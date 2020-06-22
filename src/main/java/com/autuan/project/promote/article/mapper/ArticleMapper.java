package com.autuan.project.promote.article.mapper;

import com.autuan.project.promote.article.domain.Article;
import java.util.List;

/**
 * 文章Mapper接口
 * 
 * @author autuan
 * @date 2020-06-22
 */
public interface ArticleMapper 
{
    /**
     * 查询文章
     * 
     * @param id 文章ID
     * @return 文章
     */
    public Article selectArticleById(String id);

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 删除文章
     * 
     * @param id 文章ID
     * @return 结果
     */
    public int deleteArticleById(String id);

    /**
     * 批量删除文章
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticleByIds(String[] ids);
}
