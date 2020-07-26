package com.autuan.project.promote.salesman.service;

import cn.hutool.poi.excel.ExcelWriter;
import com.autuan.project.front.entity.CalcuRewardReq;
import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.front.entity.RewardCount;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.salesman.domain.*;

import java.math.BigDecimal;
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
    CalcuRewardRes calcuReward(CalcuRewardReq req);

    /***
     * 历史记录
     * @param req 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.front.entity.HistoryRewardRes>
     * @since: 20:51 2020/6/30 
     */
    List<RewardCount> historyReward(HistoryRewardReq req);

    /***
     * 当月记录
     * @param req
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.front.entity.HistoryRewardRes>
     * @since: 20:56 2020/6/30
     */
    List<HistoryRewardRes> thisMoonReward(HistoryRewardReq req);

    /**
     * 发卡订单统计  
     * @param req 
     * @throws 
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.front.entity.HistoryRewardRes>
     * @since : 2020/7/5 13:28
     */
    List<HistoryRewardRes> bankList(HistoryRewardReq req);
    
    /***
     * 业绩排行榜
     * @param  
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.lang.Object
     * @since: 21:24 2020/6/30 
     */
    List<RankingRes> ranking();

    String importData(List<Salesman> list, boolean updateSupport);

    /***
     * 列表查询
     * @param salesman 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.salesman.domain.TabSalesman>
     * @since: 20:08 2020/7/22
     */
    List<TabSalesman> list(SalesmanQueryReq salesman);

    /**
     * 获取用于下载的数据
     * @param req 
     * @throws 
     * @author : Autuan.Yu
     * @return: java.lang.Object
     * @since : 2020/7/24 16:16  
     */
    ExcelWriter dataDown(DataDownReq req);

    DataDownRes querySalesmanReward(DataDownReq req);

    /**
     * 上月招募数量
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Object
     * @since : 2020/7/26 9:56
     */
    Integer lastMoonCount();
    /**
     * 本月招募数量
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Integer
     * @since : 2020/7/26 9:57
     */
    Integer thisMoonCount();

    /**
     *  总计招募数量
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Integer
     * @since : 2020/7/26 9:56
     */
    Integer allCount();

    /**
     * 业绩总计(首页)
     * @param  
     * @throws 
     * @author : Autuan.Yu
     * @return: java.lang.Integer
     * @since : 2020/7/26 10:22  
     */
    BigDecimal allRewardCount();
    /**
     * 上月业绩总计(首页)
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Integer
     * @since : 2020/7/26 10:22
     */
    BigDecimal lastRewardCount();
    /**
     * 本月业绩总计(首页)
     * @param
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Integer
     * @since : 2020/7/26 10:22
     */
    BigDecimal thisRewardCount();

    /**
     * 查询全部业务员业绩
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.DataDownRes
     * @since : 2020/7/26 11:09
     */
    DataDownRes querySalesmanRewardAll(DataDownReq req);

    /**
     * 下载全部业务员业绩
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Object
     * @since : 2020/7/26 11:19
     */
    ExcelWriter dataDownAll(DataDownReq req);

    /**
     * 修改业务员  
     * @param salesman 
     * @throws 
     * @author : Autuan.Yu
     * @return: int
     * @since : 2020/7/26 11:35
     */
    int updateSalesman(TabSalesman salesman);
}
