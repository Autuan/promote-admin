package com.autuan.project.front.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @className: GroupDataDetailDTO
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 11:49
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDataDetailDTO {

    private BigDecimal count;
    @JsonFormat(pattern = "yyyy-MM")
    private LocalDate date;
    private String id;
}