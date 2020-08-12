package com.autuan.project.promote.link.linkSalesmanTask.controller;

import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.page.TableDataInfo;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTaskListDTO;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskService;
import com.autuan.project.promote.task.domain.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.autuan.project.promote.base.constant.Constant.*;

/**
 * @className: SalesmanTaskCustomController
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/23 19:21
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Controller
@RequestMapping("/promote/linkSalesmanTask/custom")
public class SalesmanTaskCustomController  extends BaseController {
    @Autowired
    private ISalesmanTaskCustomService salesmanTaskCustomServicce;
    @Autowired
    private ISalesmanTaskService iSalesmanTaskService;

    /**
     * 查询业务员-任务中间列表
     */
    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/list")
    @ResponseBody
    public ReturnResult list(SalesmanTask salesmanTask) {
        SalesmanTaskListDTO dto = salesmanTaskCustomServicce.listSalesmanAndTask();
        return ReturnResult.ok(dto);
    }

    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/listByTaskId")
    @ResponseBody
    public ReturnResult listByTaskId(SalesmanTask salesmanTask) {
        List<TabSalesmanTask> res = salesmanTaskCustomServicce.listByTaskId(salesmanTask);
        return ReturnResult.ok(res);
    }

    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/pass")
    @ResponseBody
    public ReturnResult pass(String ids){
        salesmanTaskCustomServicce.verify(ids, TASK_PASS);
        return ReturnResult.ok();
    }

    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/fail")
    @ResponseBody
    public ReturnResult fail(String ids){
        salesmanTaskCustomServicce.verify(ids, TASK_FAIL);
        // 回收CODE
        salesmanTaskCustomServicce.recoveryCode(ids);
        return ReturnResult.ok();
    }

    @PostMapping("/assignCode")
    @ResponseBody
    @RequiresPermissions("promote:task:edit")
    public ReturnResult assignCode(@RequestBody TabSalesmanTask req) {
        salesmanTaskCustomServicce.assignCode(req);
        return ReturnResult.ok();
    }

    /**
     * 查询业务员-任务中间列表
     */
    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/selectSalesmanTaskList")
    @ResponseBody
    public TableDataInfo selectSalesmanTaskList(SalesmanTask salesmanTask) {
        startPage();
        List<TabSalesmanTask> list = salesmanTaskCustomServicce.selectSalesmanTaskList(salesmanTask);
        return getDataTable(list);
    }

    /**
     * 查询业务员-任务中间列表
     */
    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/listTaskCode/{taskId}")
    @ResponseBody
    public TableDataInfo listTaskCode(@PathVariable String taskId) {
        TabSalesmanTask salesmanTask = TabSalesmanTask.builder()
                .taskId(taskId)
                .build();
        startPage();
        List<TabSalesmanTask> list = salesmanTaskCustomServicce.listTaskCode(salesmanTask);
        return getDataTable(list);
    }

    /**
     * 修改业务员-任务中间
     */
    @GetMapping("/assign/code/{id}")
    public String assignCodePage(@PathVariable("id") String id, ModelMap mmap) {
        SalesmanTask salesmanTask = iSalesmanTaskService.selectSalesmanTaskById(id);
        mmap.put("salesmanTask", salesmanTask);
        return "promote/linkSalesmanTask/assignCode";
    }
}