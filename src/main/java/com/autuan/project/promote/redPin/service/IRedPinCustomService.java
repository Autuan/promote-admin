package com.autuan.project.promote.redPin.service;

import com.autuan.project.promote.redPin.domain.TabRedPin;

public interface IRedPinCustomService {
    /***
     * 新增红包码
     * @param redPin
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : void
     * @since: 20:18 2020/9/12
     */
    void add(TabRedPin redPin);

    /***
     * 删除
     * @param ids
     * @throws Throwable
     * @description:
     * @author: sen.zhou
     * @return : int
     * @since: 20:35 2020/9/12
     */
    int deleteRedPinByIds(String ids);
}
