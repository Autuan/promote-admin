package com.autuan.project.promote.salesman.controller;

import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.framework.web.page.TableDataInfo;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.domain.SalesmanQueryReq;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 业务员Controller
 *
 * @author autuan
 * @date 2020-06-19
 */
@Controller
@RequestMapping("/promote/salesman/custom")
public class SalesmanCustomController extends BaseController {
    private String prefix = "promote/salesman";
    @Autowired
    private ISalesmanService salesmanService;
    @Autowired
    private ISalesmanCustomService salesmanCustomService;

    /**
     * 查询业务员
     */
    @RequiresPermissions("promote:salesman:list")
    @PostMapping("/selectByMobile/{mobile}")
    @ResponseBody
    public ReturnResult selectByMobile(@PathVariable("mobile") String mobile) {
        TabSalesman tabSalesman = salesmanCustomService.selectByMobile(mobile);
        return ReturnResult.ok(tabSalesman);
    }

    /**
     * 查询业务员列表
     */
    @RequiresPermissions("promote:salesman:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalesmanQueryReq salesman) {
        startPage();
        List<TabSalesman> list = salesmanCustomService.list(salesman);
        return getDataTable(list);
    }



    @RequiresPermissions("promote:salesman:edit")
    @PostMapping("/resetPwd")
    @Log(title = "业务员重置密码", businessType = BusinessType.UPDATE)
    @ResponseBody
    public ReturnResult resetPwd(String ids) {
        salesmanCustomService.resetPwd(ids);
        return ReturnResult.ok();
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public ReturnResult get(@PathVariable("id") String id) {
        Salesman salesman = salesmanService.selectSalesmanById(id);
        return ReturnResult.ok(salesman);
    }

    @PostMapping("/importExcel")
    @ResponseBody
    public AjaxResult importExcel(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Salesman> util = new ExcelUtil<Salesman>(Salesman.class);
        List<Salesman> list = util.importExcel(file.getInputStream());
        String message = salesmanCustomService.importData(list, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<Salesman> util = new ExcelUtil<Salesman>(Salesman.class);
        return util.importTemplateExcel("业务员");
    }
}
