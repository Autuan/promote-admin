package com.autuan.project.promote.dataJd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @className: OptionJdRewardReq
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 17:24
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionJdRewardReq {
    private String taskId;
    private BigDecimal rewardCommon;
    private BigDecimal rewardNewbie;
    private BigDecimal rewardGold;
}