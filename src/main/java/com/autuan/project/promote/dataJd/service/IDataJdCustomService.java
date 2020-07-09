package com.autuan.project.promote.dataJd.service;

import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.promote.dataJd.domain.OptionJdRewardReq;
import com.autuan.project.promote.dataJd.domain.TabDataJd;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/1 11:10
 * @company : 上海奥若拉信息科技集团有限公司
 */
public interface IDataJdCustomService {
    /**
     * 批量导入
     * @param list
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/1 11:13
     */
    String importExcel(List<TabDataJd> list);

    /***
     * 配置京东任务奖励
     * @param req
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 17:30 2020/7/4
     */
    void optionJdReward(OptionJdRewardReq req);

    Map<String, BigDecimal> getRewardOption(Set<String> ids);

    /**
     * 按月查询数据
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.front.entity.HistoryRewardRes>
     * @since : 2020/7/5 13:27
     */
    List<HistoryRewardRes> jdList(HistoryRewardReq req);
}
