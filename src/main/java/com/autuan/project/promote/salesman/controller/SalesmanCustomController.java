package com.autuan.project.promote.salesman.controller;

import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.framework.web.page.TableDataInfo;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 业务员Controller
 * 
 * @author autuan
 * @date 2020-06-19
 */
@Controller
@RequestMapping("/promote/salesman/custom")
public class SalesmanCustomController extends BaseController
{
    private String prefix = "promote/salesman";

    @Autowired
    private ISalesmanCustomService salesmanService;

    /**
     * 查询业务员
     */
    @RequiresPermissions("promote:salesman:list")
    @PostMapping("/selectByMobile/{mobile}")
    @ResponseBody
    public ReturnResult selectByMobile(@PathVariable("mobile") String mobile)
    {
//        startPage();
        TabSalesman tabSalesman = salesmanService.selectByMobile(mobile);
        return ReturnResult.ok(tabSalesman);
//        return getDataTable(list);
    }

}