package com.autuan.project.promote.salesman.service.impl;

import cn.hutool.core.util.IdUtil;
import com.autuan.common.utils.Md5Utils;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.domain.TabSalesmanExample;
import com.autuan.project.promote.salesman.mapper.SalesmanMapper;
import com.autuan.project.promote.salesman.mapper.TabSalesmanMapper;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/18 16:42
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class SalesmanCustomServiceImpl implements ISalesmanCustomService {
    @Autowired
    private TabSalesmanMapper salesmanMapper;
    /**
     * 登录方法(返回用户信息)
     *
     * @param salesman mobile:手机号 password:密码
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.Salesman
     * @since : 2020/6/18 16:44
     */
    @Override
    public TabSalesman login(TabSalesman salesman) {
        String pwd = Md5Utils.hash(salesman.getPassword());
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
                .andMobileEqualTo(salesman.getMobile())
                .andPasswordEqualTo(pwd);
        TabSalesman tabSalesman = salesmanMapper.selectOneByExample(example);
        return tabSalesman;
    }

    /**
     * 注册用户
     *
     * @param salesman
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.TabSalesman
     * @since : 2020/6/19 15:24
     */
    @Override
    public boolean register(TabSalesman salesman) {
        salesman.setCreateTime(LocalDateTime.now());
        salesman.setCreateBy("用户注册");
        salesman.setId(IdUtil.simpleUUID());
        // todo 用户手机号唯一验证
        return  salesmanMapper.insertSelective(salesman) == 1;
    }
}
