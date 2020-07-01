package com.autuan.project.front.entity;

import com.autuan.project.promote.article.domain.TabArticle;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.task.domain.TabTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: IndexVO
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/22 19:37
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexVO {
    List<TabArticle> articles;
    List<TabArticle> images;
    List<TabTask> tasks;
    List<TabSalesmanTask> receivedList;
}