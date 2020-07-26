package com.autuan.project.front.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: GroupDataReq
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 11:44
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDataReq {
    private String groupId;
    private String querySalesmanId;
    private String dateStart;
    private String dateEnd;

}