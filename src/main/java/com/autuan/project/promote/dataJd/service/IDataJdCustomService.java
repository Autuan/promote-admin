package com.autuan.project.promote.dataJd.service;

import com.autuan.project.promote.dataJd.domain.TabDataJd;

import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/1 11:10
 * @company : 上海奥若拉信息科技集团有限公司
 */
public interface IDataJdCustomService {
    /**
     * 批量导入
     * @param list
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/1 11:13
     */
    void importExcel(List<TabDataJd> list);
}
