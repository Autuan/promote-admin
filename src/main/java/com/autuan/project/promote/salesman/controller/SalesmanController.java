package com.autuan.project.promote.salesman.controller;

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
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 业务员Controller
 *
 * @author autuan
 * @date 2020-06-29
 */
@Controller
@RequestMapping("/promote/salesman")
public class SalesmanController extends BaseController {
    private String prefix = "promote/salesman";

    @Autowired
    private ISalesmanService salesmanService;

    @RequiresPermissions("promote:salesman:view")
    @GetMapping()
    public String salesman() {
        return prefix + "/salesman";
    }

    /**
     * 查询业务员列表
     */
    @RequiresPermissions("promote:salesman:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Salesman salesman) {
        startPage();
        List<Salesman> list = salesmanService.selectSalesmanList(salesman);
        return getDataTable(list);
    }

    /**
     * 导出业务员列表
     */
    @RequiresPermissions("promote:salesman:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Salesman salesman) {
        List<Salesman> list = salesmanService.selectSalesmanList(salesman);
        ExcelUtil<Salesman> util = new ExcelUtil<Salesman>(Salesman.class);
        return util.exportExcel(list, "salesman");
    }

    /**
     * 新增业务员
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存业务员
     */
    @RequiresPermissions("promote:salesman:add")
    @Log(title = "业务员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Salesman salesman) {
        return toAjax(salesmanService.insertSalesman(salesman));
    }

    /**
     * 修改业务员
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        Salesman salesman = salesmanService.selectSalesmanById(id);
        mmap.put("salesman", salesman);
        return prefix + "/edit";
    }

    /**
     * 修改保存业务员
     */
    @RequiresPermissions("promote:salesman:edit")
    @Log(title = "业务员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Salesman salesman) {
        return toAjax(salesmanService.updateSalesman(salesman));
    }

    /**
     * 删除业务员
     */
    @RequiresPermissions("promote:salesman:remove")
    @Log(title = "业务员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(salesmanService.deleteSalesmanByIds(ids));
    }
}
