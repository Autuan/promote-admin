package com.autuan.project.front.controller;

import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ISalesmanService salesmanService;

    /**
     * 修改业务员
     */
    @RequestMapping("/{id}")
    public Object getById(@PathVariable("id") String id)
    {
        Salesman salesman = salesmanService.selectSalesmanById(id);
        return ReturnResult.ok(salesman);
//        mmap.put("salesman", salesman);
//        return prefix + "/edit";
    }
}
