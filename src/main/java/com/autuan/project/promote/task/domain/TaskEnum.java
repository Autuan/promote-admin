package com.autuan.project.promote.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: TaskStatusEnum
 * @author: sen.zhou
 * @description:
 * @date: 2020/7/4 20:50
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@AllArgsConstructor
public enum TaskEnum {
    /**
     * 普通开白条
     */
    JD_COMMON(0),
    /**
     * 京东金库
     */
    JD_GOLD(1),
    /**
     * 新手礼包
     */
    JD_NEWBIE(2),


    TYPE_NOT_USE(0),
    TYPE_USE(1),
    TYPE_DISABLE(2),
    TYPE_ABLE(3),

    STATUS_NOT_APPLY(0),
    STATUS_VERIFY_ING(1),
    STATUS_PASS(2),
    STATUS_FAIL(3);



    private Integer status;

    public Integer val(){
        return status;
    }
}