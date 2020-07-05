package com.autuan.project.promote.salesman.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 业务员对象 tab_salesman
 * 
 * @author autuan
 * @date 2020-06-29
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salesman extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;
    /** 名称 */
    @Excel(name = "姓名")
    private String name;
    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;
    /** 佣金结算卡-银行卡号 */
    @Excel(name = "佣金结算卡-银行卡号")
    private String brokerageBankNo;
    /** 佣金结算卡-银行名称 */
    @Excel(name = "佣金结算卡-银行名称")
    private String brokerageBankName;
    /** 佣金结算卡-银行开户行地区 */
    @Excel(name = "佣金结算卡-银行开户行地区")
    private String brokerageBankAddress;
    /** 密码 */
//    @Excel(name = "密码")
    private String password;
    /** 身份证号 */
    @Excel(name = "身份证号")
    private String identifyNumber;
    /** 申请日期 */
//    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime applyTime;
    /** 等级展示 */
    @Excel(name = "等级展示",defaultValue="普通会员")
    private String level;
    /** 所属小组ID */
//    @Excel(name = "所属小组ID")
    private String groupId;
    /** 学历 */
    @Excel(name = "学历")
    private String education;
    /** 0 男 1女 */
    @Excel(name = "性别",combo = "0,1")
    private Integer gender;
    /** 头像 */
//    @Excel(name = "头像")
    private String headImg;


}
