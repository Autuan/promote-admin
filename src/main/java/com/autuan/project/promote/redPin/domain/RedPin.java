package com.autuan.project.promote.redPin.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 红包码信息对象 tab_red_pin
 * 
 * @author autuan
 * @date 2020-09-09
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedPin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 业务员ID */
    @Excel(name = "业务员ID")
    private String salesmanId;
    /** 业务员名称(冗余) */
    @Excel(name = "业务员名称(冗余)")
    private String salesmanName;
    /** 京东红包码值 */
    @Excel(name = "京东红包码值")
    private String redPin;


}
