package com.autuan.project.promote.task.controller;

import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.task.domain.SetTaskParamAO;
import com.autuan.project.promote.task.domain.Task;
import com.autuan.project.promote.task.service.ITaskCustomService;
import com.autuan.project.promote.task.service.ITaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/23 14:04
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Controller
@RequestMapping("/promote/task/custom")
public class TaskCustomController {
    private String prefix = "promote/task";

    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITaskCustomService taskCustomService;
    /**
     * 设置param
     *
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.String
     * @since : 2020/6/23 11:02
     */
    @GetMapping("/param/{id}")
    public String param(@PathVariable("id") String id, ModelMap mmap) {
        Task task = taskService.selectTaskById(id);
        List<TabParam> paramList = taskCustomService.getParamList(id);
        mmap.put("task", task);
        mmap.put("paramList", paramList);
        return prefix + "/param";
    }

    @PostMapping("/setTaskParam")
    @ResponseBody
    @RequiresPermissions("promote:task:edit")
    @Log(title = "设置参数", businessType = BusinessType.INSERT)
    public ReturnResult setTaskParam(@RequestBody SetTaskParamAO ao){
        taskCustomService.setTaskParam(ao);
        return ReturnResult.ok();
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public ReturnResult edit(@PathVariable("id") String id)
    {
        Task task = taskService.selectTaskById(id);
        return ReturnResult.ok(task);
    }
}
