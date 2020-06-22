package com.autuan.project.promote.group.service;

import com.autuan.project.promote.group.domain.TabGroup;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/22 15:14
 * @company : 上海奥若拉信息科技集团有限公司
 */
public interface IGroupCustomService {
    /**
     * 查询是否已是组长
     * @throws
     * @author : Autuan.Yu
     * @return: boolean
     * @since : 2020/6/22 15:15
     */
    boolean haveThisHeader(String salesmanId);

    /**
     * 是否已加入小组
     * @param salesmanId
     * @throws
     * @author : Autuan.Yu
     * @return: boolean
     * @since : 2020/6/22 15:35
     */
    boolean joinedGroup(String salesmanId);

    /**
     * 添加小组  
     * @param group 
     * @throws 
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/22 15:31
     */
    void add(TabGroup group);

    /**
     * 修改小组
     * @param group
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/22 15:40
     */
    void edit(TabGroup group);
}
