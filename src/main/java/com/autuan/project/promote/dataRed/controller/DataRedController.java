package com.autuan.project.promote.dataRed.controller;

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
import com.autuan.project.promote.dataRed.domain.DataRed;
import com.autuan.project.promote.dataRed.service.IDataRedService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 数据导入-京东红包PINController
 * 
 * @author autuan
 * @date 2020-09-09
 */
@Controller
@RequestMapping("/promote/dataRed")
public class DataRedController extends BaseController
{
    private String prefix = "promote/dataRed";

    @Autowired
    private IDataRedService dataRedService;

    @RequiresPermissions("promote:dataRed:view")
    @GetMapping()
    public String dataRed()
    {
        return prefix + "/dataRed";
    }

    /**
     * 查询数据导入-京东红包PIN列表
     */
    @RequiresPermissions("promote:dataRed:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DataRed dataRed)
    {
        startPage();
        List<DataRed> list = dataRedService.selectDataRedList(dataRed);
        return getDataTable(list);
    }

    /**
     * 导出数据导入-京东红包PIN列表
     */
    @RequiresPermissions("promote:dataRed:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DataRed dataRed)
    {
        List<DataRed> list = dataRedService.selectDataRedList(dataRed);
        ExcelUtil<DataRed> util = new ExcelUtil<DataRed>(DataRed.class);
        return util.exportExcel(list, "dataRed");
    }

    /**
     * 新增数据导入-京东红包PIN
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存数据导入-京东红包PIN
     */
    @RequiresPermissions("promote:dataRed:add")
    @Log(title = "数据导入-京东红包PIN", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DataRed dataRed)
    {
        return toAjax(dataRedService.insertDataRed(dataRed));
    }

    /**
     * 修改数据导入-京东红包PIN
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        DataRed dataRed = dataRedService.selectDataRedById(id);
        mmap.put("dataRed", dataRed);
        return prefix + "/edit";
    }

    /**
     * 修改保存数据导入-京东红包PIN
     */
    @RequiresPermissions("promote:dataRed:edit")
    @Log(title = "数据导入-京东红包PIN", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DataRed dataRed)
    {
        return toAjax(dataRedService.updateDataRed(dataRed));
    }

    /**
     * 删除数据导入-京东红包PIN
     */
    @RequiresPermissions("promote:dataRed:remove")
    @Log(title = "数据导入-京东红包PIN", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dataRedService.deleteDataRedByIds(ids));
    }
}
