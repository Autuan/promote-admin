package com.autuan.project.front.controller;

import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/5 13:26
 * @company : 上海奥若拉信息科技集团有限公司
 */
@RestController
@RequestMapping("/front/data/jd")
public class DataJdFrontController {
    @Autowired
    private IDataJdCustomService dataJdCustomService;

    @RequestMapping("/jdList")
    public ReturnResult jdList(@RequestBody HistoryRewardReq req) {
        List<HistoryRewardRes> res = dataJdCustomService.jdList(req);
        return ReturnResult.ok(res);
    }
}
