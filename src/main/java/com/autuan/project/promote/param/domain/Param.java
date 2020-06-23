package com.autuan.project.promote.param.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 任务链接参数对象 tab_param
 * 
 * @author autuan
 * @date 2020-06-23
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Param extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;
    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String paramKey;
    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String value;
    /** 0: 自定义 1:使用业务员活动code */
    @Excel(name = "0: 自定义 1:使用业务员活动code")
    private Long type;
    /** 任务外键 */
    @Excel(name = "任务外键")
    private String taskId;


}
