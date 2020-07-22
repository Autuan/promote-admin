package com.autuan.project.promote.salesman.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @className: SalesmanQueryReq
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/22 20:16
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesmanQueryReq {
    @JsonFormat(pattern = "yyyy-MM-dd ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate queryApplyTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate queryApplyTimeEnd;

    private String id;

    private String brokerageBankNo;

    private String brokerageBankName;

    private String brokerageBankAddress;

    private String name;

    private String mobile;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;

    private String password;

    private String identifyNumber;

    private LocalDateTime applyTime;

    private String level;

    private String groupId;

    private String education;

    private Integer gender;

    private String headImg;
}