package com.autuan.project.front.controller;

import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: DataBankFrontController
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/30 21:09
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@RestController
@RequestMapping("/front/data/bank")
public class DataBankFrontController {
    @Autowired
    private ISalesmanCustomService salesmanCustomService;

    /**
     *
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.front.entity.ReturnResult
     * @since : 2020/6/30 14:02
     */
    @RequestMapping("/bankList")
    public ReturnResult bankList(@RequestBody HistoryRewardReq req) {
        List<HistoryRewardRes> res = salesmanCustomService.bankList(req);
        return ReturnResult.ok(res);
    }
}