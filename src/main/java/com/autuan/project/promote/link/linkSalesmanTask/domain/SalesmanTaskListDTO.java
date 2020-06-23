package com.autuan.project.promote.link.linkSalesmanTask.domain;

import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.task.domain.TabTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: SalesmanTaskListDTO
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/23 19:23
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesmanTaskListDTO {
    List<TabSalesman> salesmanList;
    List<TabTask> taskList;
}