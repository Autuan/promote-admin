package com.autuan.project.promote.dataBank.service;

import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBank;

import java.util.List;

public interface IDataBankCustomService {
    /***
     * excel 导入
     * @param list 
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 21:12 2020/6/25 
     */
    String importExcel(List<TabDataBank> list);


    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Deprecated
     String importData(List<DataBank> userList, Boolean isUpdateSupport);
}
