package com.autuan.project.promote.task.controller;

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
import com.autuan.project.promote.task.domain.Task;
import com.autuan.project.promote.task.service.ITaskService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 任务Controller
 *
 * @author autuan
 * @date 2020-07-04
 */
@Controller
@RequestMapping("/promote/task")
public class TaskController extends BaseController {
    private String prefix = "promote/task";

    @Autowired
    private ITaskService taskService;

    @RequiresPermissions("promote:task:view")
    @GetMapping()
    public String task() {
        return prefix + "/task";
    }

    /**
     * 查询任务列表
     */
    @RequiresPermissions("promote:task:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Task task) {
        startPage();
        List<Task> list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @RequiresPermissions("promote:task:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Task task) {
        List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 新增任务
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存任务
     */
    @RequiresPermissions("promote:task:add")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Task task) {
        return toAjax(taskService.insertTask(task));
    }

    /**
     * 修改任务
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        Task task = taskService.selectTaskById(id);
        mmap.put("task", task);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务
     */
    @RequiresPermissions("promote:task:edit")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Task task) {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除任务
     */
    @RequiresPermissions("promote:task:remove")
    @Log(title = "任务", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(taskService.deleteTaskByIds(ids));
    }
}
