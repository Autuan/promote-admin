package com.autuan.project.promote.link.linkSalesmanTask.service.impl;

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
import com.autuan.project.promote.task.service.ITaskCustomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        List<Integer> inList = Lists.newArrayList(0, 3);
        example.createCriteria()
                .andTaskIdEqualTo(salesmanTask.getTaskId())
                .andStatusEqualTo(0)
                .andTypeIn(inList);
        return tabSalesmanTaskMapper.selectByExample(example);
    }

    @Override
    public void assignCode(TabSalesmanTask req) {
        TabSalesmanTaskExample example = new TabSalesmanTaskExample();
        example.createCriteria()
                .andTaskIdEqualTo(req.getTaskId())
                .andSalesmanIdEqualTo(req.getSalesmanId());
        TabSalesmanTask one = tabSalesmanTaskMapper.selectOneByExample(example);
        if(null != one && StrUtil.isNotBlank(one.getId())) {
            throw new CustomRespondException("业务员已有此任务");
        }
        TabSalesmanTask bean = tabSalesmanTaskMapper.selectByPrimaryKey(req.getId());
        if(StrUtil.isNotBlank(bean.getSalesmanId())) {
            throw new CustomRespondException("此CODE已被使用");
        }
        bean.setSalesmanId(req.getSalesmanId());
        bean.setStatus(2);
        bean.setType(1);
        tabSalesmanTaskMapper.updateByPrimaryKeySelective(bean);
    }


}