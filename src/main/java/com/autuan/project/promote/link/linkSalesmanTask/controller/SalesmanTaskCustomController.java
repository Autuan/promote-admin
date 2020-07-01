package com.autuan.project.promote.link.linkSalesmanTask.controller;

import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTaskListDTO;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskCustomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}