package com.autuan.project.promote.link.linkSalesmanTask.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.text.Convert;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTaskListDTO;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TaskEnum;
import com.autuan.project.promote.task.service.ITaskCustomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className: SalesmanTaskCustomServiceImpl
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/23 19:21
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Service
@Slf4j
public class SalesmanTaskCustomServiceImpl implements ISalesmanTaskCustomService {
    @Autowired
    private ISalesmanCustomService salesmanCustomService;
    @Autowired
    private ITaskCustomService taskCustomService;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    @Override
    public SalesmanTaskListDTO listSalesmanAndTask() {
        List<TabSalesman> salesmanList = salesmanCustomService.listSalesmanThousand();
        List<TabTask> taskList = taskCustomService.listTaskThousand();
        return SalesmanTaskListDTO.builder()
                .taskList(taskList)
                .salesmanList(salesmanList)
                .build();
    }

    /**
     * 修改审核闫
     *
     * @param ids
     * @param i
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/1 15:57
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void verify(String ids, int i) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        List<String> idList = Arrays.asList(Convert.toStrArray(ids));

        example.createCriteria()
                .andIdIn(idList);
        TabSalesmanTask bean = TabSalesmanTask.builder()
                .status(i)
                .updateTime(LocalDateTime.now())
                .updateBy(ShiroUtils.getLoginName())
                .build();
        tabSalesmanTaskMapper.updateByExampleSelective(bean,example);

    }

    @Override
    public List<TabSalesmanTask> listByTaskId(SalesmanTask salesmanTask) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        List<Integer> inList = Lists.newArrayList(TaskEnum.TYPE_ABLE.val(),TaskEnum.TYPE_NOT_USE.val());
        example.createCriteria()
                .andTaskIdEqualTo(salesmanTask.getTaskId())
                .andStatusEqualTo(TaskEnum.STATUS_NOT_APPLY.val())
                .andTypeIn(inList);
        example.setOrderByClause("code asc");
        return tabSalesmanTaskMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignCode(TabSalesmanTask req) {
        // todo comment
        // todo del extra code
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        List<Integer> inList = Lists.newArrayList(TaskEnum.TYPE_ABLE.val(),TaskEnum.TYPE_NOT_USE.val());
        example.createCriteria()
                .andCodeIsNotNull()
                .andTypeIn(inList)
                .andTaskIdEqualTo(req.getTaskId())
                .andSalesmanIdEqualTo(req.getSalesmanId());

        TabSalesmanTask one = tabSalesmanTaskMapper.selectOneByExample(example);
        if(null != one && StrUtil.isNotBlank(one.getId())) {
            throw new CustomRespondException("业务员已有此任务");
        }


        example.clear();
        example.createCriteria()
                .andTaskIdEqualTo(req.getTaskId())
                .andSalesmanIdIsNull()
                .andStatusEqualTo(0)
                .andTypeEqualTo(0)
                .andCodeEqualTo(req.getCode());
        TabSalesmanTask bean = tabSalesmanTaskMapper.selectOneByExample(example);
        bean.setSalesmanId(req.getSalesmanId());
        bean.setStatus(TaskEnum.STATUS_PASS.val());
        bean.setType(TaskEnum.TYPE_USE.val());
        bean.setUpdateBy(ShiroUtils.getLoginName());
        bean.setUpdateTime(LocalDateTime.now());
        tabSalesmanTaskMapper.updateByPrimaryKeySelective(bean);
        // 删除用来展示的空CODE列表
        example.clear();
        example.createCriteria()
                .andTaskIdEqualTo(req.getTaskId())
                .andSalesmanIdEqualTo(req.getSalesmanId())
                .andCodeIsNull();
        tabSalesmanTaskMapper.deleteByExample(example);
    }

    @Override
    public List<TabSalesmanTask> selectSalesmanTaskList(SalesmanTask salesmanTask) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        TabSalesmanTaskExample.Criteria criteria = example.createCriteria();
        criteria.andSalesmanIdIsNotNull();
        if(StrUtil.isNotBlank(salesmanTask.getTaskId())) {
            criteria.andTaskIdEqualTo(salesmanTask.getTaskId());
        }
        if(StrUtil.isNotBlank(salesmanTask.getCode())) {
            criteria.andCodeLike("%"+salesmanTask.getCode()+"%");
        }
        if(StrUtil.isNotBlank(salesmanTask.getSalesmanId())) {
            criteria.andSalesmanIdEqualTo(salesmanTask.getSalesmanId());
        }
        if(null != salesmanTask.getStatus()) {
            criteria.andStatusEqualTo(salesmanTask.getStatus());
        }

        List<TabSalesmanTask> tabSalesmanTasks = tabSalesmanTaskMapper.selectByExample(example);
        return tabSalesmanTasks;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recoveryCode(String ids) {
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();

        List<String> idList = Arrays.asList(Convert.toStrArray(ids));
        if (CollectionUtil.isEmpty(idList)) {
            return;
        }
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        example.createCriteria()
                .andIdIn(idList);
        List<TabSalesmanTask> tasks = tabSalesmanTaskMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(tasks)) {
            return;
        }
        TabSalesmanTask bean = new TabSalesmanTask();
        bean.setStatus(TaskEnum.STATUS_FAIL.val());
        bean.setType(TaskEnum.TYPE_DISABLE.val());
        bean.setUpdateTime(now);
        bean.setUpdateBy(loginName);
        tabSalesmanTaskMapper.updateByExampleSelective(bean,example);

        List<TabSalesmanTask> insertList = new ArrayList<>();
        example.clear();
        for(TabSalesmanTask task : tasks) {
            TabSalesmanTask insertBean = TabSalesmanTask.builder()
                    .id(IdUtil.simpleUUID())
                    .taskId(task.getTaskId())
                    .code(task.getCode())
                    .createBy(loginName)
                    .createTime(now)
                    .status(TaskEnum.STATUS_NOT_APPLY.val())
                    .type(TaskEnum.TYPE_NOT_USE.val())
                    .build();
            insertList.add(insertBean);
            example.or()
                    .andCodeEqualTo(task.getCode())
                    .andTaskIdEqualTo(task.getTaskId())
                    .andSalesmanIdIsNull();
        }
        tabSalesmanTaskMapper.deleteByExample(example);
        tabSalesmanTaskMapper.batchInsert(insertList);

    }

    @Override
    public List<TabSalesmanTask> listTaskCode(TabSalesmanTask salesmanTask) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        TabSalesmanTaskExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(salesmanTask.getTaskId());
        criteria.andCodeIsNotNull();
        // 2 停用 todo magic val
        criteria.andTypeNotEqualTo(2);

        List<TabSalesmanTask> tabSalesmanTasks = tabSalesmanTaskMapper.selectByExample(example);
        return tabSalesmanTasks;
    }


}