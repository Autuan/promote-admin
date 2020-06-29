package com.autuan.project.promote.task.service.impl;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.front.entity.GeneratorQrCodeVO;
import com.autuan.project.front.entity.ReceiveAO;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.param.domain.TabParamExample;
import com.autuan.project.promote.param.mapper.TabParamMapper;
import com.autuan.project.promote.task.domain.SetTaskParamAO;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import com.autuan.project.promote.task.mapper.TabTaskMapper;
import com.autuan.project.promote.task.service.ITaskCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        example.limit(20);
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

            QrConfig config = new QrConfig(130, 130);
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
                    290,
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
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        example.createCriteria()
                .andTaskIdEqualTo(ao.getTaskId())
                .andSalesmanIdEqualTo(ao.getSalesmanId());
        TabSalesmanTask one = tabSalesmanTaskMapper.selectOneByExample(example);
        if(one != null && StrUtil.isNotBlank(one.getId())) {
            return;
        }
        TabSalesmanTask bind = TabSalesmanTask.builder()
                .taskId(ao.getTaskId())
                .salesmanId(ao.getSalesmanId())
                .code(IdUtil.objectId())
                .createTime(LocalDateTime.now())
                .id(IdUtil.simpleUUID())
                .build();
        tabSalesmanTaskMapper.insertSelective(bind);

    }
}
