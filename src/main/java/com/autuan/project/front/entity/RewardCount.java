package com.autuan.project.front.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @className: RewardCount
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/8 21:30
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardCount {
    @JsonFormat(pattern = "yyyy-MM")
    private LocalDateTime month;
    private BigDecimal count;
}