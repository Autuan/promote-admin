package com.autuan.project.promote.group.controller;

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
import com.autuan.project.promote.group.domain.Group;
import com.autuan.project.promote.group.service.IGroupService;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.common.utils.poi.ExcelUtil;
import com.autuan.framework.web.page.TableDataInfo;

/**
 * 小组Controller
 * 
 * @author autuan
 * @date 2020-06-22
 */
@Controller
@RequestMapping("/promote/group")
public class GroupController extends BaseController
{
    private String prefix = "promote/group";

    @Autowired
    private IGroupService groupService;

    @RequiresPermissions("promote:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询小组列表
     */
    @RequiresPermissions("promote:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Group group)
    {
        startPage();
        List<Group> list = groupService.selectGroupList(group);
        return getDataTable(list);
    }

    /**
     * 导出小组列表
     */
    @RequiresPermissions("promote:group:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Group group)
    {
        List<Group> list = groupService.selectGroupList(group);
        ExcelUtil<Group> util = new ExcelUtil<Group>(Group.class);
        return util.exportExcel(list, "group");
    }

    /**
     * 新增小组
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小组
     */
    @RequiresPermissions("promote:group:add")
    @Log(title = "小组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Group group)
    {
        return toAjax(groupService.insertGroup(group));
    }

    /**
     * 修改小组
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Group group = groupService.selectGroupById(id);
        mmap.put("group", group);
        return prefix + "/edit";
    }

    /**
     * 修改保存小组
     */
    @RequiresPermissions("promote:group:edit")
    @Log(title = "小组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Group group)
    {
        return toAjax(groupService.updateGroup(group));
    }

    /**
     * 删除小组
     */
    @RequiresPermissions("promote:group:remove")
    @Log(title = "小组", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(groupService.deleteGroupByIds(ids));
    }
}
