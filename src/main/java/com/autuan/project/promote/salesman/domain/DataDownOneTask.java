package com.autuan.project.promote.salesman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @className: DataDownOneTask
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/24 19:26
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDownOneTask {
    private String taskId;
    private String num;
    private BigDecimal reward;
}