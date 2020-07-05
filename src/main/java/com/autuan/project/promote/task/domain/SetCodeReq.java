package com.autuan.project.promote.task.domain;

import lombok.Data;

/**
 * @className: SetCodeReq
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/2 21:15
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
public class SetCodeReq {
    private Integer digit;
    private String prefix;
    private String taskId;
    private Integer num;
    private Integer allNum;
    private String inputCode;
}