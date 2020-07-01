package com.autuan.project.promote.dataJd.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 京东联合拉新数据对象 tab_data_jd
 * 
 * @author autuan
 * @date 2020-07-01
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataJd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 记录时间 */
    @Excel(name = "记录时间")
    private LocalDateTime recordTime;
    /** 任务链接内部ID */
    @Excel(name = "任务链接内部ID")
    private String taskInnerId;
    /** 任务链接 */
    @Excel(name = "任务链接")
    private String taskUrl;
    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;
    /** 任务名称 */
    @Excel(name = "任务名称")
    private String orderName;
    /** 一级渠道 */
    @Excel(name = "一级渠道")
    private String channelFirst;
    /** 下游渠道来源 */
    @Excel(name = "下游渠道来源")
    private String channelBelowSource;
    /** 打卡是否有效 0-否 1-是 */
    @Excel(name = "打卡是否有效 0-否 1-是")
    private Integer checkStatus;
    /** 打卡失效原因 */
    @Excel(name = "打卡失效原因")
    private String checkReason;
    /** 用户参与京东pin（脱敏） */
    @Excel(name = "用户参与京东pin", readConverterExp = "脱=敏")
    private String joinJdPin;
    /** 用户参与推广链接 */
    @Excel(name = "用户参与推广链接")
    private String joinLink;
    /** 用户参与时间 */
    @Excel(name = "用户参与时间")
    private LocalDateTime joinTime;
    /** 用户参与订单号 */
    @Excel(name = "用户参与订单号")
    private String joinOrder;
    /** 白条开通 pin（脱敏） */
    @Excel(name = "白条开通 pin", readConverterExp = "脱=敏")
    private String openJdCreditPin;
    /** 白条开通链接 */
    @Excel(name = "白条开通链接")
    private String openJdCreditUrl;
    /** 白条开通时间 */
    @Excel(name = "白条开通时间")
    private LocalDateTime openJdCreditTime;
    /** 白条开通方式 0-普通开白条 1-小金库白条 */
    @Excel(name = "白条开通方式 0-普通开白条 1-小金库白条")
    private Integer openJdCreditType;
    /** 白条首单订单号 */
    @Excel(name = "白条首单订单号")
    private String jdCreditFirstOrderNo;
    /** 白条首单pin（脱敏） */
    @Excel(name = "白条首单pin", readConverterExp = "脱=敏")
    private String jdCreditFirstOrderPin;
    /** 白条首单交易时间 */
    @Excel(name = "白条首单交易时间")
    private LocalDateTime jdCreditFirstOrderTime;
    /** 白条首单推广链接 */
    @Excel(name = "白条首单推广链接")
    private String jdCreditFirstOrderUrl;
    /** 银行+订单号 */
    @Excel(name = "银行+订单号")
    private String bankAndOrderNo;
    /** 银行+pin（脱敏） */
    @Excel(name = "银行+pin", readConverterExp = "脱=敏")
    private String bankAndPin;
    /** 银行+入金金额 */
    @Excel(name = "银行+入金金额")
    private String bankAndIncomeMoney;
    /** 银行+入金时间 */
    @Excel(name = "银行+入金时间")
    private LocalDateTime bankAndIncomeTime;
    /** 银行+推广链接 */
    @Excel(name = "银行+推广链接")
    private String bankAndPromoteUrl;
    /** 用户参与与白条首单时间差 */
    @Excel(name = "用户参与与白条首单时间差")
    private String joinedFirstOrderWithJdCreditTimeDifference;
    /** 用户参与与银行+入金时间差 */
    @Excel(name = "用户参与与银行+入金时间差")
    private String joinedFirstOrderWithBankIncomeTimeDifference;
    /** 白条开通首单是否同日 0-否 1-是 */
    @Excel(name = "白条开通首单是否同日 0-否 1-是")
    private Integer jdCreditOpenOneDay;
    /** 新手礼包操作时间 */
    @Excel(name = "新手礼包操作时间")
    private LocalDateTime newbiePackageOperTime;
    /** 新手礼包pin（脱敏） */
    @Excel(name = "新手礼包pin", readConverterExp = "脱=敏")
    private String newbiePackagePin;
    /** 新手礼包链接ID */
    @Excel(name = "新手礼包链接ID")
    private String newbiePackageUrlId;
    /** 新手礼包操作结果 0-fail 1-success */
    @Excel(name = "新手礼包操作结果 0-fail 1-success")
    private Integer newbiePackageResult;
    /** 金条贷款订单号 */
    @Excel(name = "金条贷款订单号")
    private String jdGoldOrderNo;
    /** 金条是否首次贷款 0:否 1:是 */
    @Excel(name = "金条是否首次贷款 0:否 1:是")
    private Integer jdGoldIsFirst;
    /** 金条贷款完成时间 */
    @Excel(name = "金条贷款完成时间")
    private LocalDateTime jdGoldConfirmTime;
    /** 新手礼包海帆奖励Id */
    @Excel(name = "新手礼包海帆奖励Id")
    private String jdNewHandRewardId;


}
