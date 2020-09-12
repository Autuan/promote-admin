package com.autuan.project.promote.redPin.controller;

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
import com.autuan.project.promote.redPin.domain.RedPin;
import com.autuan.project.promote.redPin.service.IRedPinService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 红包码信息Controller
 * 
 * @author autuan
 * @date 2020-09-09
 */
@Controller
@RequestMapping("/promote/redPin")
public class RedPinController extends BaseController
{
    private String prefix = "promote/redPin";

    @Autowired
    private IRedPinService redPinService;

    @RequiresPermissions("promote:redPin:view")
    @GetMapping()
    public String redPin()
    {
        return prefix + "/redPin";
    }

    /**
     * 查询红包码信息列表
     */
    @RequiresPermissions("promote:redPin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RedPin redPin)
    {
        startPage();
        List<RedPin> list = redPinService.selectRedPinList(redPin);
        return getDataTable(list);
    }

    /**
     * 导出红包码信息列表
     */
    @RequiresPermissions("promote:redPin:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RedPin redPin)
    {
        List<RedPin> list = redPinService.selectRedPinList(redPin);
        ExcelUtil<RedPin> util = new ExcelUtil<RedPin>(RedPin.class);
        return util.exportExcel(list, "redPin");
    }

    /**
     * 新增红包码信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存红包码信息
     */
    @RequiresPermissions("promote:redPin:add")
    @Log(title = "红包码信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RedPin redPin)
    {
        return toAjax(redPinService.insertRedPin(redPin));
    }

    /**
     * 修改红包码信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        RedPin redPin = redPinService.selectRedPinById(id);
        mmap.put("redPin", redPin);
        return prefix + "/edit";
    }

    /**
     * 修改保存红包码信息
     */
    @RequiresPermissions("promote:redPin:edit")
    @Log(title = "红包码信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RedPin redPin)
    {
        return toAjax(redPinService.updateRedPin(redPin));
    }

    /**
     * 删除红包码信息
     */
    @RequiresPermissions("promote:redPin:remove")
    @Log(title = "红包码信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(redPinService.deleteRedPinByIds(ids));
    }
}
