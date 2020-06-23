package com.autuan.project.promote.param.controller;

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
import com.autuan.project.promote.param.domain.Param;
import com.autuan.project.promote.param.service.IParamService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 任务链接参数Controller
 * 
 * @author autuan
 * @date 2020-06-23
 */
@Controller
@RequestMapping("/promote/param")
public class ParamController extends BaseController
{
    private String prefix = "promote/param";

    @Autowired
    private IParamService paramService;

    @RequiresPermissions("promote:param:view")
    @GetMapping()
    public String param()
    {
        return prefix + "/param";
    }

    /**
     * 查询任务链接参数列表
     */
    @RequiresPermissions("promote:param:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Param param)
    {
        startPage();
        List<Param> list = paramService.selectParamList(param);
        return getDataTable(list);
    }

    /**
     * 导出任务链接参数列表
     */
    @RequiresPermissions("promote:param:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Param param)
    {
        List<Param> list = paramService.selectParamList(param);
        ExcelUtil<Param> util = new ExcelUtil<Param>(Param.class);
        return util.exportExcel(list, "param");
    }

    /**
     * 新增任务链接参数
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务链接参数
     */
    @RequiresPermissions("promote:param:add")
    @Log(title = "任务链接参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Param param)
    {
        return toAjax(paramService.insertParam(param));
    }

    /**
     * 修改任务链接参数
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Param param = paramService.selectParamById(id);
        mmap.put("param", param);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务链接参数
     */
    @RequiresPermissions("promote:param:edit")
    @Log(title = "任务链接参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Param param)
    {
        return toAjax(paramService.updateParam(param));
    }

    /**
     * 删除任务链接参数
     */
    @RequiresPermissions("promote:param:remove")
    @Log(title = "任务链接参数", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(paramService.deleteParamByIds(ids));
    }
}
