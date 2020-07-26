package com.autuan.project.front.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/30 10:53
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalcuRewardReq {
    private String salesmanId;
//    private LocalDateTime queryMoon;
    private LocalDate queryMoon;
}
