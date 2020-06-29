package com.autuan.project.promote.salesman.service;

import com.autuan.project.promote.salesman.domain.CalcuRewardRes;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.domain.TabSalesman;

import java.util.List;

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
    TabSalesman login(TabSalesman salesman);

    /**
     * 注册用户
     * @param salesman
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.TabSalesman
     * @since : 2020/6/19 15:24
     */
    boolean register(TabSalesman salesman);

    /**
     * 根据手机号查询用户
     * @param salesman
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.TabSalesman
     */
    TabSalesman selectByMobile(String mobile);

    /**
     * 重置密码
     * @param ids
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/22 16:51
     */
    void resetPwd(String ids);

    /**
     * 修改密码
     * @param salesmanId
 * @param pwd
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/29 11:15
     */
    void updatePwd(TabSalesman salesman);

    /**
     * 获取一千条业务员  
     * @param  
     * @throws 
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.salesman.domain.TabSalesman>
     * @since : 2020/6/29 14:03  
     */
    List<TabSalesman> listSalesmanThousand();
    
    /**
     * 计算收益  
     * @param  
     * @throws 
     * @author : Autuan.Yu
     * @return: java.lang.Object
     * @since : 2020/6/29 14:20
     */
    CalcuRewardRes calcuReward(String salesmanId);
}
