package com.autuan.project.promote.dataBank.controller;

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
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.service.IDataBankService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 数据导入-开卡订单Controller
 * 
 * @author autuan
 * @date 2020-07-04
 */
@Controller
@RequestMapping("/promote/dataBank")
public class DataBankController extends BaseController
{
    private String prefix = "promote/dataBank";

    @Autowired
    private IDataBankService dataBankService;

    @RequiresPermissions("promote:dataBank:view")
    @GetMapping()
    public String dataBank()
    {
        return prefix + "/dataBank";
    }

    /**
     * 查询数据导入-开卡订单列表
     */
    @RequiresPermissions("promote:dataBank:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DataBank dataBank)
    {
        startPage();
        List<DataBank> list = dataBankService.selectDataBankList(dataBank);
        return getDataTable(list);
    }

    /**
     * 导出数据导入-开卡订单列表
     */
    @RequiresPermissions("promote:dataBank:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DataBank dataBank)
    {
        List<DataBank> list = dataBankService.selectDataBankList(dataBank);
        ExcelUtil<DataBank> util = new ExcelUtil<DataBank>(DataBank.class);
        return util.exportExcel(list, "dataBank");
    }

    /**
     * 新增数据导入-开卡订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存数据导入-开卡订单
     */
    @RequiresPermissions("promote:dataBank:add")
    @Log(title = "数据导入-开卡订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DataBank dataBank)
    {
        return toAjax(dataBankService.insertDataBank(dataBank));
    }

    /**
     * 修改数据导入-开卡订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        DataBank dataBank = dataBankService.selectDataBankById(id);
        mmap.put("dataBank", dataBank);
        return prefix + "/edit";
    }

    /**
     * 修改保存数据导入-开卡订单
     */
    @RequiresPermissions("promote:dataBank:edit")
    @Log(title = "数据导入-开卡订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DataBank dataBank)
    {
        return toAjax(dataBankService.updateDataBank(dataBank));
    }

    /**
     * 删除数据导入-开卡订单
     */
    @RequiresPermissions("promote:dataBank:remove")
    @Log(title = "数据导入-开卡订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dataBankService.deleteDataBankByIds(ids));
    }
}
