package com.autuan.project.promote.dataRed.service;

import com.autuan.project.promote.dataRed.domain.TabDataRed;

import java.util.List;

public interface IDataRedCustomService {
    /***
     * EXCEL 导入
     * @param list
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : java.lang.String
     * @since: 21:22 2020/9/13
     */
    String importExcel(List<TabDataRed> list);
}
