package com.autuan.project.promote.dataJd.controller;

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
import com.autuan.project.promote.dataJd.domain.DataJd;
import com.autuan.project.promote.dataJd.service.IDataJdService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 京东联合拉新数据Controller
 * 
 * @author autuan
 * @date 2020-07-05
 */
@Controller
@RequestMapping("/promote/dataJd")
public class DataJdController extends BaseController
{
    private String prefix = "promote/dataJd";

    @Autowired
    private IDataJdService dataJdService;

    @RequiresPermissions("promote:dataJd:view")
    @GetMapping()
    public String dataJd()
    {
        return prefix + "/dataJd";
    }

    /**
     * 查询京东联合拉新数据列表
     */
    @RequiresPermissions("promote:dataJd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DataJd dataJd)
    {
        startPage();
        List<DataJd> list = dataJdService.selectDataJdList(dataJd);
        return getDataTable(list);
    }

    /**
     * 导出京东联合拉新数据列表
     */
    @RequiresPermissions("promote:dataJd:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DataJd dataJd)
    {
        List<DataJd> list = dataJdService.selectDataJdList(dataJd);
        ExcelUtil<DataJd> util = new ExcelUtil<DataJd>(DataJd.class);
        return util.exportExcel(list, "dataJd");
    }

    /**
     * 新增京东联合拉新数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存京东联合拉新数据
     */
    @RequiresPermissions("promote:dataJd:add")
    @Log(title = "京东联合拉新数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DataJd dataJd)
    {
        return toAjax(dataJdService.insertDataJd(dataJd));
    }

    /**
     * 修改京东联合拉新数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        DataJd dataJd = dataJdService.selectDataJdById(id);
        mmap.put("dataJd", dataJd);
        return prefix + "/edit";
    }

    /**
     * 修改保存京东联合拉新数据
     */
    @RequiresPermissions("promote:dataJd:edit")
    @Log(title = "京东联合拉新数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DataJd dataJd)
    {
        return toAjax(dataJdService.updateDataJd(dataJd));
    }

    /**
     * 删除京东联合拉新数据
     */
    @RequiresPermissions("promote:dataJd:remove")
    @Log(title = "京东联合拉新数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dataJdService.deleteDataJdByIds(ids));
    }
}
