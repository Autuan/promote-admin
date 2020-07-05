package com.autuan.project.front.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/30 14:02
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Data
public class HistoryRewardReq {
    private String salesmanId;
//    @JsonFormat(pattern = "yyyy-MM")
//    @DateTimeFormat(pattern = "yyyy-MM")
    private String queryDateStr;

    // 方法传参---->
    private LocalDateTime queryDateEnd;
    private LocalDateTime queryDateStart;
}
