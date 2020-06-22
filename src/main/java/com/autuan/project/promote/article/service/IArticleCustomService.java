package com.autuan.project.promote.article.service;

import com.autuan.project.promote.article.domain.TabArticle;

import java.util.List;

public interface IArticleCustomService {
    /***
     * 獲取首頁推薦文章
     * @param
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.article.domain.TabArticle>
     * @since: 19:33 2020/6/22
     */
    List<TabArticle> getCarouselArticle();

    /***
     * 獲取首頁推薦圖片
     * @param
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.article.domain.TabArticle>
     * @since: 19:34 2020/6/22
     */
    List<TabArticle> getCarouselImg();
    
    /***
     * 獲取文章列表
     * @param article 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.util.List<com.autuan.project.promote.article.domain.TabArticle>
     * @since: 20:55 2020/6/22
     */
    List<TabArticle> list(TabArticle article);
}
