package com.autuan.project.promote.article.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
            import java.time.LocalDateTime;
    import java.time.LocalDateTime;
                    import com.autuan.common.utils.security.ShiroUtils;
            import com.autuan.common.utils.security.ShiroUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.article.mapper.ArticleMapper;
import com.autuan.project.promote.article.domain.Article;
import com.autuan.project.promote.article.service.IArticleService;
import com.autuan.common.utils.text.Convert;

/**
 * 文章Service业务层处理
 * 
 * @author autuan
 * @date 2020-06-22
 */
@Service
public class ArticleServiceImpl implements IArticleService 
{
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询文章
     * 
     * @param id 文章ID
     * @return 文章
     */
    @Override
    public Article selectArticleById(String id)
    {
        return articleMapper.selectArticleById(id);
    }

    /**
     * 查询文章列表
     * 
     * @param article 文章
     * @return 文章
     */
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增文章
     * 
     * @param article 文章
     * @return 结果
     */
    @Override
    public int insertArticle(Article article)
    {
                    article.setCreateTime(LocalDateTime.now());
                            article.setCreateBy(ShiroUtils.getLoginName());
                        article.setId(IdUtil.simpleUUID());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改文章
     * 
     * @param article 文章
     * @return 结果
     */
    @Override
    public int updateArticle(Article article)
    {
                    article.setUpdateTime(LocalDateTime.now());
                                article.setUpdateBy(ShiroUtils.getLoginName());
                    return articleMapper.updateArticle(article);
    }

    /**
     * 删除文章对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArticleByIds(String ids)
    {
        return articleMapper.deleteArticleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章信息
     * 
     * @param id 文章ID
     * @return 结果
     */
    @Override
    public int deleteArticleById(String id)
    {
        return articleMapper.deleteArticleById(id);
    }
}
