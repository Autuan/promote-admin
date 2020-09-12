package com.autuan.project.promote.redPin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: CheckDO
 * @author: sen.zhou
 * @description:
 * @date: 2020/9/12 21:10
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckDO {
    private String id;
    private Integer num;
}