package com.autuan.project.promote.salesman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/29 14:10
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalcuRewardRes {
    private BigDecimal historyReward;
    private BigDecimal queryMoonReward;

    public static CalcuRewardRes zero(){
        return new CalcuRewardRes(BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
