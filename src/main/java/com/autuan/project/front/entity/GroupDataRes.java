package com.autuan.project.front.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @className: GroupDataRes
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 11:47
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDataRes {
    private String salesmanId;
    private String salesmanName;
    private List<GroupDataDetailDTO> detail;
}