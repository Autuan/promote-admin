package com.autuan.project.promote.task.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.front.entity.GeneratorQrCodeVO;
import com.autuan.project.front.entity.ReceiveAO;
import com.autuan.project.promote.dataJd.domain.OptionJdRewardReq;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.param.domain.TabParamExample;
import com.autuan.project.promote.param.mapper.TabParamMapper;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.domain.TabSalesmanExample;
import com.autuan.project.promote.salesman.mapper.TabSalesmanMapper;
import com.autuan.project.promote.task.domain.*;
import com.autuan.project.promote.task.mapper.TabTaskMapper;
import com.autuan.project.promote.task.mapper.TaskMapper;
import com.autuan.project.promote.task.service.ITaskCustomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/23 14:41
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class TaskCustomServiceImpl implements ITaskCustomService {
    @Autowired
    private TabParamMapper paramMapper;
    @Autowired
    private TabTaskMapper tabTaskMapper;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    @Autowired
    private IDataJdCustomService dataJdCustomService;
    @Autowired
    private TabSalesmanMapper salesmanMapper;

    /**
     * 设置参数
     *
     * @param ao
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/23 14:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setTaskParam(SetTaskParamAO ao) {
        List<TabParam> list = ao.getList();
        String taskId = list.get(0).getTaskId();
        TabParamExample example = new TabParamExample();
        example.createCriteria()
                .andTaskIdEqualTo(taskId);
        // 删除后重新添加
        paramMapper.deleteByExample(example);
        for (TabParam param : list) {
            param.setCreateTime(LocalDateTime.now());
            param.setCreateBy(ShiroUtils.getLoginName());
            param.setId(IdUtil.simpleUUID());
        }
        paramMapper.batchInsert(list);
    }

    @Override
    public List<TabSalesmanTask> listForCode(String taskId) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        example.createCriteria()
                .andTypeNotEqualTo(TaskEnum.TYPE_DISABLE.val())
                .andCodeIsNotNull()
                .andTaskIdEqualTo(taskId);
        return tabSalesmanTaskMapper.selectByExample(example);
    }

    /**
     * 获取设置的配置
     *
     * @param taskId@throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.param.domain.TabParam>
     * @since : 2020/6/23 14:49
     */
    @Override
    public List<TabParam> getParamList(String taskId) {
        TabParamExample example = new TabParamExample();
        example.createCriteria()
                .andTaskIdEqualTo(taskId);
        return paramMapper.selectByExample(example);
    }

    /**
     * 获取首页task
     *
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.task.domain.TabTask>
     * @since : 2020/6/23 15:59
     */
    @Override
    public List<TabTask> getIndexTask() {
        TabTaskExample example = new TabTaskExample();
        example.setOrderByClause("priority desc");
        example.limit(100);
        example.createCriteria()
                .andIsHiddenEqualTo(0);
        return tabTaskMapper.selectByExample(example);
    }

    /**
     * 生成二维码
     *
     * @throws
     * @author : Autuan.Yu
     * @return: byte[]
     * @since : 2020/6/23 16:31
     */
    @Override
    public void generatorQrcode(GeneratorQrCodeVO vo, ServletOutputStream outputStream) {
        try {
            String taskId = vo.getTaskId();
            String salesmanId = vo.getSalesmanId();
            TabTask task = tabTaskMapper.selectByPrimaryKey(taskId);
            // 获取背景图片
            BufferedImage src = ImgUtil.read(new URL(task.getBgImg()));
            // 生成二维码
            //      获取内容
            TabParamExample paramExample = new TabParamExample();
            paramExample.createCriteria()
                    .andTaskIdEqualTo(taskId);
            List<TabParam> paramList = paramMapper.selectByExample(paramExample);
            TabSalesmanTaskExample tabSalesmanTaskExample = new TabSalesmanTaskExample();
            tabSalesmanTaskExample.createCriteria()
                    .andTypeEqualTo(TaskEnum.TYPE_USE.val())
                    .andStatusEqualTo(TaskEnum.STATUS_PASS.val())
                    .andSalesmanIdEqualTo(salesmanId)
                    .andTaskIdEqualTo(taskId);
            TabSalesmanTask link = tabSalesmanTaskMapper.selectOneByExample(tabSalesmanTaskExample);
            StringBuilder content = new StringBuilder("");
            for (TabParam param : paramList) {
                if (param.getType().equals(0)) {
                    content = content.append(param.getParamKey()).append("=").append(param.getValue()).append("&");
                } else if (param.getType().equals(1)) {
                    content = content.append(param.getParamKey()).append("=").append(link.getCode()).append("&");

                }
            }
            StringBuilder prefix = new StringBuilder(task.getPrefix());
            if (StrUtil.contains(prefix, "?")) {
                content = prefix.append("&").append(content);
            } else {
                content = prefix.append("?").append(content);
            }

            QrConfig config = new QrConfig(250, 250);
            // 设置边距，既二维码和背景之间的边距
            config.setMargin(1);
            // 设置前景色，既二维码颜色（青色）
            config.setForeColor(Color.BLACK);
            // 设置背景色（灰色）
            config.setBackColor(Color.WHITE);
            BufferedImage qrCodeImg = QrCodeUtil.generate(content.substring(0, content.length() - 1), config);
            // 合成图片
            ImgUtil.pressImage(
                    // 背景图
                    src,
                    // 输出位置
                    outputStream,
                    //水印图片
                    qrCodeImg,
                    //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                    0,
                    //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                    700,
                    1f
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取一千条任务
     * @param
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.task.domain.TabTask>
     * @since: 20:28 2020/6/23
     */
    @Override
    public List<TabTask> listTaskThousand() {
        TabTaskExample example = new TabTaskExample();
        example.limit(1000);
        return tabTaskMapper.selectByExample(example);
    }


    @Override
    public void receive(ReceiveAO ao) {
        // todo del extra code
//        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
//        example.createCriteria()
//                .andTaskIdEqualTo(ao.getTaskId())
//                .andSalesmanIdEqualTo(ao.getSalesmanId());
//        TabSalesmanTask one = tabSalesmanTaskMapper.selectOneByExample(example);
//        if(one != null && StrUtil.isNotBlank(one.getId())) {
//            if(Integer.valueOf(0).equals(one.getStatus())) {
//                one.setStatus(1);
//                tabSalesmanTaskMapper.updateByPrimaryKey(one);
//                return;
//            } else {
//                return;
//            }
//        }
//        example.clear();
//        List<Integer> inList = Lists.newArrayList(0, 3);
//        example.createCriteria()
//                .andSalesmanIdIsNull()
//                .andTypeIn(inList)
//                .andTaskIdEqualTo(ao.getTaskId());
//        TabSalesmanTask bind = tabSalesmanTaskMapper.selectOneByExample(example);
//        if(null == bind || StrUtil.isBlank(bind.getId())) {
//            throw new CustomRespondException("CODE已用尽");
//        }
//        bind.setType(1);
//        bind.setStatus(1);
//        bind.setSalesmanId(ao.getSalesmanId());
//        bind.setUpdateTime(LocalDateTime.now());
//        tabSalesmanTaskMapper.updateByPrimaryKeySelective(bind);

        TabSalesmanTask bean = TabSalesmanTask.builder()
                .id(IdUtil.simpleUUID())
                .taskId(ao.getTaskId())
                .salesmanId(ao.getSalesmanId())
                // todo magic code
                .status(1)
                .type(0)
                .createTime(LocalDateTime.now())

                .build();

        tabSalesmanTaskMapper.insertSelective(bean);
    }

    /**
     * 批量领取
     *
     * @param ao
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/7 15:56
     */
    @Override
    public void batchReceive(ReceiveAO ao) {
        List<TabSalesmanTask> insertList = new ArrayList<>();
        for(String salesmanId : ao.getSalesmanIds()) {
        TabSalesmanTask bean = TabSalesmanTask.builder()
                .id(IdUtil.simpleUUID())
                .taskId(ao.getTaskId())
                .salesmanId(salesmanId)
                // todo magic code
                .status(1)
                .type(0)
                .createTime(LocalDateTime.now())
                .build();
            insertList.add(bean);
        }

        tabSalesmanTaskMapper.batchInsert(insertList);
    }

    /**
     * 已领取任务
     *
     * @param salesmanId
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask>
     * @since : 2020/7/1 16:16
     */
    @Override
    public List<TabSalesmanTask> receivedTask(String salesmanId) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        example.createCriteria()
                .andSalesmanIdEqualTo(salesmanId);
        return tabSalesmanTaskMapper.selectByExample(example);
    }

    @Override
    public void setCode(SetCodeReq req) {
        String taskId = req.getTaskId();
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        example.createCriteria()
                .andTaskIdEqualTo(req.getTaskId())
                .andTypeNotEqualTo(2)
                .andCodeEqualTo(req.getInputCode());
        TabSalesmanTask one = tabSalesmanTaskMapper.selectOneByExample(example);
        if( null != one) {
            throw new CustomRespondException("CODE : " + req.getInputCode() + " 已存在");
        }

        tabSalesmanTaskMapper.insertSelective(TabSalesmanTask.builder()
                .id(IdUtil.simpleUUID())
                .taskId(taskId)
                .code(req.getInputCode())
                .createTime(now)
                .createBy(loginName)
                // todo magic code
                .status(0)
                .type(0)
                .build());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(TaskAddReq req) {
        TabTask addBean = TabTask.builder().build();
        BeanUtil.copyProperties(req, addBean);

        String taskId =  IdUtil.simpleUUID();
        addBean.setCreateTime(LocalDateTime.now());
        addBean.setCreateBy(ShiroUtils.getLoginName());
        addBean.setId(taskId);

        tabTaskMapper.insertSelective(addBean);
        // 如果是京东方式,添加京东多级任务奖励
        // todo magic num
        if(Integer.valueOf(1).equals(req.getAssignType())) {
        OptionJdRewardReq optReq = OptionJdRewardReq.builder()
                .taskId(taskId)
                .rewardCommon(req.getRewardCommon())
                .rewardGold(req.getRewardGold())
                .rewardNewbie(req.getRewardNewbie())
                .build();
        dataJdCustomService.optionJdReward(optReq);
        }
    }

    /**
     * 获取未领取任务的业务员,最多50条
     *
     * @param id
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.salesman.domain.TabSalesman>
     * @since : 2020/7/7 15:38
     */
    @Override
    public List<TabSalesman> getNotReceiveSalesmanByTaskId(String id) {
        // 获取已经有此任务的会员
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        List<Integer> inList = Lists.newArrayList(TaskEnum.TYPE_ABLE.val(),TaskEnum.TYPE_USE.val(),TaskEnum.TYPE_NOT_USE.val());
        List<Integer> statusInList = Lists.newArrayList(TaskEnum.STATUS_VERIFY_ING.val(),TaskEnum.STATUS_PASS.val());
        salesmanTaskExample.createCriteria()
                .andTaskIdEqualTo(id)
                .andSalesmanIdIsNotNull()
                .andStatusIn(statusInList)
                .andTypeIn(inList);
        List<TabSalesmanTask> receivedLinkList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        // 获取未领取任务的会员
        TabSalesmanExample salesmanExample = new TabSalesmanExample();
        TabSalesmanExample.Criteria salesmanCriteria = salesmanExample.createCriteria();
        if(CollectionUtil.isNotEmpty(receivedLinkList)) {
            salesmanCriteria.andIdNotIn(receivedLinkList.stream()
                                        .map(TabSalesmanTask::getSalesmanId).collect(Collectors.toList())) ;
        }
        List<TabSalesman> salesmen = salesmanMapper.selectByExample(salesmanExample);
        return salesmen;
    }

    @Override
    public int changeHiddenVal(ChangeHiddenValReq req) {
        if(CollectionUtil.isEmpty(req.getIds())) {
            throw new CustomRespondException("没有要修改的数据");
        }
        TabTaskExample example = new TabTaskExample();
        example.createCriteria()
                .andIdIn(req.getIds());
        TabTask bean = TabTask.builder()
.isHidden(req.getVal())
                .build();

        int i = tabTaskMapper.updateByExampleSelective(bean, example);
        return i;
    }

}
