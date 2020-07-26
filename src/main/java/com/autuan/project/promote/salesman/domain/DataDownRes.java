package com.autuan.project.promote.salesman.domain;

import com.autuan.project.promote.task.domain.TabTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @className: DataDownRes
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/24 19:07
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDownRes {
    /**
     * 任务列表
     */
    private List<TabTask> taskList;
    /**
     * 列表每一行
     */
    private List<DataDownTr> trList;
    /**
     * 所有业务员业绩和
     */
    private BigDecimal allCountReward;
}