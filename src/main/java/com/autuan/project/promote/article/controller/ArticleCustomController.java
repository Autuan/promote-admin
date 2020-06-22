package com.autuan.project.promote.article.controller;

import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.article.domain.TabArticle;
import com.autuan.project.promote.article.service.IArticleCustomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: ArticleCustomController
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/22 20:43
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Controller
@RequestMapping("/promote/article/custom")
public class ArticleCustomController {
    @Autowired
    private IArticleCustomService articleCustomService;
    /**
     * 查询文章列表
     */
    @RequiresPermissions("promote:article:list")
    @PostMapping("/list")
    @ResponseBody
    public ReturnResult list(TabArticle article)
    {
        List<TabArticle> list = articleCustomService.list(article);
        return ReturnResult.ok(list);
    }
}