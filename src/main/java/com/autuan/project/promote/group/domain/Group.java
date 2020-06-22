package com.autuan.project.promote.group.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 小组对象 tab_group
 * 
 * @author autuan
 * @date 2020-06-22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 小组名称 */
    @Excel(name = "小组名称")
    private String name;
    /** 小组组长 */
    @Excel(name = "小组组长")
    private String groupHeader;
    /** 组长名称(冗余) */
    @Excel(name = "组长名称(冗余)")
    private String headerName;
    /** 组长手机号(冗余) */
    @Excel(name = "组长手机号(冗余)")
    private String headerMobile;


}
