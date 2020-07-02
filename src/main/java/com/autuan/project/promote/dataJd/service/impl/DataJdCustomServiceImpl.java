package com.autuan.project.promote.dataJd.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.mapper.TabDataJdMapper;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import com.autuan.project.promote.task.mapper.TabTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/1 11:13
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class DataJdCustomServiceImpl implements IDataJdCustomService {
    @Autowired
    private TabDataJdMapper dataJdMapper;
    @Autowired
    private TabTaskMapper tabTaskMapper;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    /**
     * 批量导入
     *
     * @param list
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/1 11:13
     */
    @Override
    public void importExcel(List<TabDataJd> list) {
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();

        // 使用code 和 name 分别查出 taskId 和 salesmanId
        // taskId
        TabTaskExample tabTaskExample = new TabTaskExample();
        for (TabDataJd data : list) {
            tabTaskExample.or()
                    .andNameEqualTo(data.getOrderName());
        }
        List<TabTask> taskList = tabTaskMapper.selectByExample(tabTaskExample);
        Map<String, String> taskMap = taskList.stream()
                .collect(Collectors.toMap(TabTask::getName, TabTask::getId));
        // salesmanId
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        for (TabDataJd data : list) {
            String taskId = taskMap.get(data.getOrderName());
            if(StrUtil.isNotBlank(taskId)) {
                salesmanTaskExample.or()
                        .andCodeEqualTo(data.getJoinLink())
                        .andTaskIdEqualTo(taskId);
            }
        }
        List<TabSalesmanTask> tabSalesmanTaskList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(salesmanTaskExample.getOredCriteria())) {
            tabSalesmanTaskList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        }
        Map<String, String> linkMap = tabSalesmanTaskList.stream()
                .collect(Collectors.toMap(TabSalesmanTask::getCode, TabSalesmanTask::getSalesmanId));

        List<TabDataJd> insertList = new ArrayList<>();
        for (TabDataJd data : list) {
            String taskId = taskMap.get(data.getOrderName());
            String salesmanId = linkMap.get(data.getJoinLink());
            if(StrUtil.isBlank(taskId) || StrUtil.isBlank(salesmanId)) {
                continue;
            }
            data.setTaskId(taskId);
            data.setSalesmanId(salesmanId);
            data.setCreateTime(now);
            data.setCreateBy(loginName);
            data.setId(IdUtil.simpleUUID());
            insertList.add(data);
        }
        if(CollectionUtil.isNotEmpty(insertList)) {
            dataJdMapper.batchInsert(insertList);
        }
    }
}
