package com.autuan.project.front.controller;

import com.autuan.project.front.entity.CalcuRewardReq;
import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.salesman.domain.CalcuRewardRes;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/18 15:47
 * @company : 上海奥若拉信息科技集团有限公司
 */
@RestController
@RequestMapping("/front/salesman")
public class SalesmanFrontController {
    @Autowired
    private ISalesmanCustomService salesmanCustomService;

    /**
     * 修改业务员
     */
    @RequestMapping("/login")
    public Object login(@RequestBody TabSalesman salesman) {
        TabSalesman result = salesmanCustomService.login(salesman);
        return null==result?ReturnResult.error("账户或密码错误"): ReturnResult.ok(result);
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePwd")
    public Object updatePwd(@RequestBody TabSalesman salesman) {
        salesmanCustomService.updatePwd(salesman);
        return ReturnResult.ok();
    }

    @RequestMapping("/register")
    public Object register(@RequestBody TabSalesman salesman) {
        boolean result = salesmanCustomService.register(salesman);
        return result? ReturnResult.ok(salesman) : ReturnResult.error("注册失败,请稍后重试!");
    }

    /**
     * 计算会员中心首页的收益
     */
    @RequestMapping("/calcuReward")
    public ReturnResult calcuReward(@RequestBody CalcuRewardReq req) {
        CalcuRewardRes res = salesmanCustomService.calcuReward(req.getSalesmanId());
        return ReturnResult.ok(res);
    }

    /**
     * 历史推广费用
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.front.entity.ReturnResult
     * @since : 2020/6/30 14:02
     */
    @RequestMapping("/historyReward")
    public ReturnResult historyReward(@RequestBody HistoryRewardReq req) {
        salesmanCustomService.historyReward(req);
        return ReturnResult.ok();
    }
}
