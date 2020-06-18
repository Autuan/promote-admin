package com.autuan.project.promote.salesman.service.impl;

import com.autuan.common.utils.Md5Utils;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.mapper.SalesmanMapper;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/18 16:42
 * @company : 上海奥若拉信息科技集团有限公司
 */
public class SalesmanCustomServiceImpl implements ISalesmanCustomService {
    @Autowired
    private SalesmanMapper salesmanMapper;
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
    public Salesman login(Salesman salesman) {
        String pwd = Md5Utils.hash(salesman.getPassword());

        return null;
    }
}
