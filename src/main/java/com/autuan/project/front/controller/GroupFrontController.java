package com.autuan.project.front.controller;

import com.autuan.project.front.entity.*;
import com.autuan.project.promote.group.service.IGroupCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: GroupFrontController
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 11:42
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@RestController
@RequestMapping("/front/group")
public class GroupFrontController {
    @Autowired
    private IGroupCustomService groupCustomService;

    @RequestMapping("/groupData")
    public ReturnResult groupData(@RequestBody GroupDataReq req) {
        List<GroupDataRes> res = groupCustomService.groupData(req);
        return ReturnResult.ok(res);
    }

    /***
     * 查询会员是否有资格查看小组数据
     * @param req
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : com.autuan.project.front.entity.ReturnResult
     * @since: 17:45 2020/7/12
     */
    @RequestMapping("/groupDataPower")
    public ReturnResult groupDataPower(@RequestBody GroupDataReq req) {
        boolean bool = groupCustomService.groupDataPower(req);
        return ReturnResult.ok(bool);
    }
}