package com.autuan.project.front.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @className: HistoryRewardRes
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/30 19:58
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRewardRes {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime verifyDate;
    private String approveStatus;
    private String name;
    private String orderNo;
    private BigDecimal reward;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime applyTime;
    private String type;
    private String info;

}