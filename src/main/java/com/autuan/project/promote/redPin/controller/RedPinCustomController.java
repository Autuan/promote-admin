package com.autuan.project.promote.redPin.controller;

import com.autuan.framework.aspectj.lang.annotation.Log;
import com.autuan.framework.aspectj.lang.enums.BusinessType;
import com.autuan.framework.web.controller.BaseController;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.redPin.domain.TabRedPin;
import com.autuan.project.promote.redPin.service.IRedPinCustomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: RedPinCustomController
 * @author: sen.zhou
 * @description:
 * @date: 2020/9/12 20:13
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Controller
@RequestMapping("/promote/redPin/custom")
public class RedPinCustomController extends BaseController {
    @Autowired
    private IRedPinCustomService redPinCustomService;

    @PostMapping("/add")
    @ResponseBody
    public ReturnResult add(@RequestBody TabRedPin redPin) {
        redPinCustomService.add(redPin);
        return ReturnResult.ok();
    }

    /**
     * 删除红包码信息
     */
    @RequiresPermissions("promote:redPin:remove")
    @Log(title = "红包码信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(redPinCustomService.deleteRedPinByIds(ids));
    }
}