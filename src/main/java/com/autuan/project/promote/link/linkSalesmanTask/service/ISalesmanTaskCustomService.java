package com.autuan.project.promote.link.linkSalesmanTask.service;

import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTaskListDTO;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;

import java.util.List;

public interface ISalesmanTaskCustomService {
    SalesmanTaskListDTO listSalesmanAndTask();

    /**
     * 修改审核
     * @param ids
     * @param i
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/1 15:58
     */
    void verify(String ids, int i);

    List<TabSalesmanTask> listByTaskId(SalesmanTask salesmanTask);

    void assignCode(TabSalesmanTask req);

    /***
     * 列表查询
     * @param salesmanTask 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask>
     * @since: 19:59 2020/7/4 
     */
    List<TabSalesmanTask> selectSalesmanTaskList(SalesmanTask salesmanTask);

    /***
     * 回收CODE
     * @param req 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 19:55 2020/7/5
     */
    void recoveryCode(String ids);

    /***
     * code 管理使用
     * todo 和 listByTaskId 合并
     * @param salesmanTask
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask>
     * @since: 20:36 2020/7/23
     */
    List<TabSalesmanTask> listTaskCode(TabSalesmanTask salesmanTask);
}
