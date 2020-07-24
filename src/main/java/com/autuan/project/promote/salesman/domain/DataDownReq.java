package com.autuan.project.promote.salesman.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/24 16:14
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDownReq {
    private List<String> ids;
}
