package com.autuan.project.promote.link.linkSalesmanTask.controller;

import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTaskListDTO;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
public class SalesmanTaskCustomController {
    @Autowired
    private ISalesmanTaskCustomService salesmanTaskCustomServicce;

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
        // 2-通过  3-拒绝
        salesmanTaskCustomServicce.verify(ids, 2);
        return ReturnResult.ok();
    }

    @RequiresPermissions("promote:linkSalesmanTask:list")
    @PostMapping("/fail")
    @ResponseBody
    public ReturnResult fail(String ids){
        // 2-通过  3-拒绝
        salesmanTaskCustomServicce.verify(ids, 3);
        return ReturnResult.ok();
    }

    @PostMapping("/assignCode")
    @ResponseBody
    @RequiresPermissions("promote:task:edit")
//    @Log(title = "设置参数", businessType = BusinessType.INSERT)
    public ReturnResult assignCode(@RequestBody TabSalesmanTask req) {
        salesmanTaskCustomServicce.assignCode(req);
        return ReturnResult.ok();
    }
}