package com.autuan.project.front.controller;

import com.autuan.project.front.entity.CalcuRewardReq;
import com.autuan.project.front.entity.IndexVO;
import com.autuan.project.front.entity.ReturnResult;
import com.autuan.project.promote.article.domain.TabArticle;
import com.autuan.project.promote.article.service.IArticleCustomService;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.service.ITaskCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: IndexFrontController
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/22 19:28
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@RestController
@RequestMapping("/front/index")
public class IndexFrontController {
    @Autowired
    private IArticleCustomService articleCustomService;
    @Autowired
    private ITaskCustomService taskCustomService;

    /***
     * 獲取首頁信息
     * @param salesman
     * @throws Throwable
     * @description:
     * @since: 19:29 2020/6/22
     */
    @RequestMapping("/info")
    public ReturnResult info(@RequestBody CalcuRewardReq req) {
        // 文章輪播
        List<TabArticle> articles = articleCustomService.getCarouselArticle();
        // 圖片輪播
        List<TabArticle> images = articleCustomService.getCarouselImg();
        // 任务
        List<TabTask> tasks = taskCustomService.getIndexTask();
        // 已领取任务
        List<TabSalesmanTask> receivedList = taskCustomService.receivedTask(req.getSalesmanId());
        IndexVO result = IndexVO.builder()
                .articles(articles)
                .images(images)
                .tasks(tasks)
                .receivedList(receivedList)
                .build();

        return ReturnResult.ok(result);
    }
}