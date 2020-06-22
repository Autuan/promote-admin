package com.autuan.project.promote.group.controller;

import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.group.domain.Group;
import com.autuan.project.promote.group.domain.TabGroup;
import com.autuan.project.promote.group.service.IGroupCustomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/22 15:18
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Controller
@RequestMapping("/promote/group/custom")
public class GroupCustomController {
    @Autowired
    private IGroupCustomService groupCustomService;
    /**
     * 新增保存小组
     */
    @RequiresPermissions("promote:group:add")
    @Log(title = "小组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public ReturnResult add(TabGroup group) {
        groupCustomService.add(group);
        return ReturnResult.ok();
    }


    /**
     * 修改小组
     */
    @RequiresPermissions("promote:group:edit")
    @Log(title = "小组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public ReturnResult edit(TabGroup group) {
        groupCustomService.edit(group);
        return ReturnResult.ok();
    }

    @RequiresPermissions("promote:group:list")
    @PostMapping("/list")
    @ResponseBody
    public ReturnResult list() {
        List<TabGroup> list = groupCustomService.list();
        return ReturnResult.ok(list);
    }
}
