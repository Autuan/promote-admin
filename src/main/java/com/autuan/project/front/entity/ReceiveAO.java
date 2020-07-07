package com.autuan.project.front.entity;

import lombok.Data;

import java.util.List;

/**
 * @className: ReceiveAO
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/28 19:33
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
public class ReceiveAO {
    private String taskId;
    private String salesmanId;
    private List<String> salesmanIds;
}