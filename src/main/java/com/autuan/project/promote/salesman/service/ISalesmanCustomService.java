package com.autuan.project.promote.salesman.service;

import com.autuan.project.promote.salesman.domain.Salesman;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/18 16:42
 * @company : 上海奥若拉信息科技集团有限公司
 */
public interface ISalesmanCustomService {
    /**
     * 登录方法(返回用户信息)
     * @param salesman mobile:手机号 password:密码
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.Salesman
     * @since : 2020/6/18 16:44
     */
    Salesman login(Salesman salesman);
}
