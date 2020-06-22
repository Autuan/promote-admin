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
 * @date 2020-06-22
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


}
