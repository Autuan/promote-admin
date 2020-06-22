package com.autuan.project.front.controller;

import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.article.domain.Article;
import com.autuan.project.promote.article.domain.TabArticle;
import com.autuan.project.promote.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: ArticleFrontController
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/22 20:08
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@RestController
@RequestMapping("/front/article")
public class ArticleFrontController {
    @Autowired
    private IArticleService articleService;

    @RequestMapping("/detail/{id}")
    public ReturnResult info(@PathVariable("id") String id) {
        Article article = articleService.selectArticleById(id);
        return ReturnResult.ok(article);
    }
}