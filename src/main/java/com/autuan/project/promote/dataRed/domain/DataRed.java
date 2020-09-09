package com.autuan.project.promote.dataRed.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 数据导入-京东红包PIN对象 tab_data_red
 * 
 * @author autuan
 * @date 2020-09-09
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataRed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 红包码 */
    @Excel(name = "红包码")
    private String redPin;
    /** 奖励额 */
    @Excel(name = "奖励额")
    private Double reward;
    /** 记录时间 */
    @Excel(name = "记录时间")
    private Long recordTime;


}
