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
 * @date 2020-07-02
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
    /** 0-未申请 1-申请中 2-申请通过 3-拒绝 */
    @Excel(name = "0-未申请 1-申请中 2-申请通过 3-拒绝")
    private Integer status;
    /** 0-未使用 1-已使用 2-已停用 3-已回收(可以使用) */
    @Excel(name = "0-未使用 1-已使用 2-已停用 3-已回收(可以使用)")
    private Integer type;


}
