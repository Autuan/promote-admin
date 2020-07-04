package com.autuan.project.promote.link.linkSalesmanTask.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 业务员-任务中间Controller
 *
 * @author autuan
 * @date 2020-07-02
 */
@Controller
@RequestMapping("/promote/linkSalesmanTask")
public class SalesmanTaskController extends BaseController {
    private String prefix = "promote/linkSalesmanTask";

    @Autowired
    private ISalesmanTaskService salesmanTaskService;

    @RequiresPermissions("promote:linkSalesmanTask:view")
    @GetMapping()
    public String linkSalesmanTask() {
        return prefix + "/linkSalesmanTask";
    }

    /**
     * 查询业务员-任务中间列表
     */
    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalesmanTask salesmanTask) {
        startPage();
        List<SalesmanTask> list = salesmanTaskService.selectSalesmanTaskList(salesmanTask);
        return getDataTable(list);
    }

    /**
     * 导出业务员-任务中间列表
     */
    @RequiresPermissions("promote:linkSalesmanTask:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SalesmanTask salesmanTask) {
        List<SalesmanTask> list = salesmanTaskService.selectSalesmanTaskList(salesmanTask);
        ExcelUtil<SalesmanTask> util = new ExcelUtil<SalesmanTask>(SalesmanTask.class);
        return util.exportExcel(list, "linkSalesmanTask");
    }

    /**
     * 新增业务员-任务中间
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存业务员-任务中间
     */
    @RequiresPermissions("promote:linkSalesmanTask:add")
    @Log(title = "业务员-任务中间", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SalesmanTask salesmanTask) {
        return toAjax(salesmanTaskService.insertSalesmanTask(salesmanTask));
    }

    /**
     * 修改业务员-任务中间
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        SalesmanTask salesmanTask = salesmanTaskService.selectSalesmanTaskById(id);
        mmap.put("salesmanTask", salesmanTask);
        return prefix + "/edit";
    }

    /**
     * 修改保存业务员-任务中间
     */
    @RequiresPermissions("promote:linkSalesmanTask:edit")
    @Log(title = "业务员-任务中间", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SalesmanTask salesmanTask) {
        return toAjax(salesmanTaskService.updateSalesmanTask(salesmanTask));
    }

    /**
     * 删除业务员-任务中间
     */
    @RequiresPermissions("promote:linkSalesmanTask:remove")
    @Log(title = "业务员-任务中间", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(salesmanTaskService.deleteSalesmanTaskByIds(ids));
    }
}
