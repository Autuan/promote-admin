package com.autuan.project.promote.task.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.page.TableDataInfo;
import com.autuan.project.front.entity.ReceiveAO;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.task.domain.*;
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
public class TaskCustomController extends BaseController {
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

    @GetMapping("/code/{id}")
    public String code(@PathVariable("id") String id, ModelMap mmap) {
        Task task = taskService.selectTaskById(id);
        List<TabSalesmanTask> list = taskCustomService.listForCode(id);
        mmap.put("allNum", list.size());
        // todo magic str
        long usedNum = list.stream().filter(item -> TaskEnum.TYPE_USE.val().equals(item.getType())).count();
        long unusedNum = list.stream().filter(item -> TaskEnum.TYPE_NOT_USE.val().equals(item.getType())).count();
        long recoveryNum = list.stream().filter(item -> TaskEnum.TYPE_ABLE.val().equals(item.getType())).count();
        mmap.put("task", task);
        mmap.put("usedNum", usedNum);
        mmap.put("unusedNum", unusedNum);
        mmap.put("recoveryNum", recoveryNum);
        return prefix + "/code";
    }

    @PostMapping("/setTaskParam")
    @ResponseBody
    @RequiresPermissions("promote:task:edit")
    @Log(title = "设置参数", businessType = BusinessType.INSERT)
    public ReturnResult setTaskParam(@RequestBody SetTaskParamAO ao) {
        taskCustomService.setTaskParam(ao);
        return ReturnResult.ok();
    }

    @PostMapping("/setCode")
    @ResponseBody
    @RequiresPermissions("promote:task:edit")
    @Log(title = "设置参数", businessType = BusinessType.INSERT)
    public ReturnResult setCode(@RequestBody SetCodeReq req) {
        taskCustomService.setCode(req);
        return ReturnResult.ok();
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public ReturnResult get(@PathVariable("id") String id) {
        Task task = taskService.selectTaskById(id);
        return ReturnResult.ok(task);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnResult add(@RequestBody TaskAddReq req) {
        taskCustomService.add(req);
        return ReturnResult.ok();
    }

    @GetMapping("/receiveTask/{id}")
    public String receiveTask(@PathVariable("id") String id, ModelMap mmap) {
        Task task = taskService.selectTaskById(id);
//        List<TabParam> paramList = taskCustomService.getParamList(id);
        mmap.put("task", task);
        List<TabSalesman> salesmen = taskCustomService.getNotReceiveSalesmanByTaskId(id);
        mmap.put("salesmen", salesmen);
        return prefix + "/receiveTask";
    }

    @RequestMapping("/batchReceive")
    @ResponseBody
    public ReturnResult batchReceive(@RequestBody ReceiveAO req) {
        taskCustomService.batchReceive(req);
        return ReturnResult.ok();
    }


    @RequestMapping("/changeHiddenVal")
    @ResponseBody
    public ReturnResult changeHiddenVal(@RequestBody ChangeHiddenValReq req) {
        taskCustomService.changeHiddenVal(req);
        return ReturnResult.ok();
    }
}
