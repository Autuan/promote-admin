package com.autuan.project.promote.article.domain;

import com.autuan.framework.aspectj.lang.annotation.Excel;
import com.autuan.framework.web.domain.BaseEntity;
import java.time.LocalDateTime;
    import java.time.LocalDateTime;
    import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 文章对象 tab_article
 * 
 * @author autuan
 * @date 2020-06-22
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 标题 */
    @Excel(name = "标题")
    private String title;
    /** 内容 */
    @Excel(name = "内容")
    private String content;
    /** 轮播图 */
    @Excel(name = "轮播图")
    private String courseImg;
    /** 缩略图 */
    @Excel(name = "缩略图")
    private String image;
    /** 标签 */
    @Excel(name = "标签")
    private String tags;
    /** 展示方式 0-不展示  1-首页轮播 2-首页推荐 */
    @Excel(name = "展示方式 0-不展示  1-首页轮播 2-首页推荐")
    private Integer type;


}
