package com.autuan.project.promote.salesman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @className: RankingRes
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 16:35
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingRes {
    private String headImg;
    private String name;
    private BigDecimal amount;
}