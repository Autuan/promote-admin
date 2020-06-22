package com.autuan.project.promote.link.linkSalesmanTask.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 业务员-任务中间对象 tab_salesman_task
 * 
 * @author autuan
 * @date 2020-06-22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesmanTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;
    /** 业务员id */
    @Excel(name = "业务员id")
    private String salesmanId;
    /** 任务员对应专属code */
    @Excel(name = "任务员对应专属code")
    private String code;
    /** 专属链接 */
    @Excel(name = "专属链接")
    private String link;
    /** 奖励额 */
    @Excel(name = "奖励额")
    private Double reward;
    /** 奖励开始时间 */
    @Excel(name = "奖励开始时间")
    private Long rewardBeginTime;
    /** 奖励结束时间 */
    @Excel(name = "奖励结束时间")
    private Long rewardStopTime;


}
