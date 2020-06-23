package com.autuan.project.front.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/23 17:34
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorQrCodeVO {
    private String taskId;
    private String salesmanId;
}
