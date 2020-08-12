package com.autuan.project.promote.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: ChangeHiddenValReq
 * @author: sen.zhou
 * @description:
 * @date: 2020/8/4 19:52
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeHiddenValReq {
    private List<String> ids;
    private Integer val;
}