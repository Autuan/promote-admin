package com.autuan.project.promote.link.linkSalesmanTask.service.impl;

import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTaskListDTO;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.service.ITaskCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public SalesmanTaskListDTO listSalesmanAndTask() {
        List<TabSalesman> salesmanList = salesmanCustomService.listSalesmanThousand();
        List<TabTask> taskList = taskCustomService.listTaskThousand();
        return SalesmanTaskListDTO.builder()
                .taskList(taskList)
                .salesmanList(salesmanList)
                .build();
    }
}