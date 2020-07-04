package com.autuan.project.promote.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @className: TaskAddReq
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 17:46
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddReq {
    /** $column.columnComment */
    private String id;
    /** 任务名称 */
    private String name;
    /** 任务链接前缀 */
    private String prefix;
    /** 外键:参数表 */
    private String paramId;
    /** 奖励额 */
    private Double reward;
    /** 奖励开始时间 */
    private LocalDateTime rewardBeginTime;
    /** 奖励结束时间 */
    private LocalDateTime rewardStopTime;
    /** 标签 ',' 分割 */
    private String tags;
    /** 文章ID(推广攻略) */
    private String articleId;
    /** 优先级 */
    private Integer priority;
    /** 文章名(推广攻略) */
    private String articleTitle;
    /** 图片 */
    private String image;
    /** 背景图 */
    private String bgImg;
    /** 简介 */
    private String summary;
    /** code 前缀 */
    private String codePrefix;
    /** 前缀位数 */
    private Integer codePrefixDigit;
    /** 0-默认 1-京东三种分配模式 */
    private Integer assignType;
    /** 索引名 */
    private String indexName;

    private BigDecimal rewardCommon;
    private BigDecimal rewardNewbie;
    private BigDecimal rewardGold;
}