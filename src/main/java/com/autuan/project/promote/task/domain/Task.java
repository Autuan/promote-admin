package com.autuan.project.promote.task.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 任务对象 tab_task
 * 
 * @author autuan
 * @date 2020-07-04
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;
    /** 任务名称 */
    @Excel(name = "任务名称")
    private String name;
    /** 任务链接前缀 */
    @Excel(name = "任务链接前缀")
    private String prefix;
    /** 外键:参数表 */
    @Excel(name = "外键:参数表")
    private String paramId;
    /** 奖励额 */
    @Excel(name = "奖励额")
    private Double reward;
    /** 奖励开始时间 */
    @Excel(name = "奖励开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rewardBeginTime;
    /** 奖励结束时间 */
    @Excel(name = "奖励结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rewardStopTime;
    /** 标签 ',' 分割 */
    @Excel(name = "标签 ',' 分割")
    private String tags;
    /** 文章ID(推广攻略) */
    @Excel(name = "文章ID(推广攻略)")
    private String articleId;
    /** 优先级 */
    @Excel(name = "优先级")
    private Integer priority;
    /** 文章名(推广攻略) */
    @Excel(name = "文章名(推广攻略)")
    private String articleTitle;
    /** 图片 */
    @Excel(name = "图片")
    private String image;
    /** 背景图 */
    @Excel(name = "背景图")
    private String bgImg;
    /** 简介 */
    @Excel(name = "简介")
    private String summary;
    /** code 前缀 */
    @Excel(name = "code 前缀")
    private String codePrefix;
    /** 前缀位数 */
    @Excel(name = "前缀位数")
    private Integer codePrefixDigit;
    /** 0-默认 1-京东三种分配模式 */
    @Excel(name = "0-默认 1-京东三种分配模式")
    private Integer assignType;
    /** 索引名 */
    @Excel(name = "索引名")
    private String indexName;

    private Integer isHidden;
}
