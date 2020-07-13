package com.autuan.project.promote.dataBank.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 数据导入-开卡订单对象 tab_data_bank
 * 
 * @author autuan
 * @date 2020-07-04
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    // ------------ 自定义 ----------------
    private String queryTimeStart;
    private String queryTimeEnd;
    // ------------ OVER -----------------

    /** id */
    private String id;
    /** 申请日期 */
    @Excel(name = "申请日期")
    private LocalDateTime applyDate;
    /** 审核日期 */
    @Excel(name = "审核日期")
    private LocalDateTime verifyDate;
    /** 申请ID */
    @Excel(name = "申请ID")
    private String applyId;
    /** 申请 状态 0-拒绝 1-通过 */
    @Excel(name = "申请状态",combo = "拒绝,通过")
    private Integer approveStatus;
    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;
    /** 手机号 */
    @Excel(name = "手机号")
    private String cMobile;
    /** 用户名 */
    @Excel(name = "用户名")
    private String cName;
    /** 渠道code */
    @Excel(name = "渠道code")
    private String channelCode;
    /** custom_flag  0-否 1-是 */
    @Excel(name = "custom_flag",combo = "否,是")
    private Integer customFlag;
    /** 任务id */
//    @Excel(name = "任务id")
    private String taskId;
    /** 所属业务员 */
//    @Excel(name = "所属业务员")
    private String salesmanId;
    /** 当前记录奖励额 */
//    @Excel(name = "当前记录奖励额")
    private Double reward;


}
