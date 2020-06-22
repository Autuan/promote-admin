package com.autuan.project.promote.article.service.impl;

import cn.hutool.core.util.StrUtil;
import com.autuan.project.promote.article.domain.TabArticle;
import com.autuan.project.promote.article.domain.TabArticleExample;
import com.autuan.project.promote.article.mapper.TabArticleMapper;
import com.autuan.project.promote.article.service.IArticleCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: ArticleCustomServiceImpl
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/22 19:31
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Service
@Slf4j
public class ArticleCustomServiceImpl implements IArticleCustomService {
    @Autowired
    private TabArticleMapper articleMapper;
    @Override
    public List<TabArticle> getCarouselArticle() {
        TabArticleExample example = new TabArticleExample().page(0,5);
        example.createCriteria()
                .andTypeEqualTo(1);
        return articleMapper.selectByExample(example);
    }

    @Override
    public List<TabArticle> getCarouselImg() {
        TabArticleExample example = new TabArticleExample().page(0,5);
        example.createCriteria()
                .andTypeEqualTo(2);
        return articleMapper.selectByExample(example);
    }

    @Override
    public List<TabArticle> list(TabArticle article) {
        TabArticleExample example = new TabArticleExample().page(0,50);
        TabArticleExample.Criteria criteria = example.createCriteria();
        if(StrUtil.isNotBlank(article.getTitle())) {
            criteria.andTitleLike("%"+article.getTitle()+"%");
        }
        return articleMapper.selectByExample(example);
//        return null;
    }
}