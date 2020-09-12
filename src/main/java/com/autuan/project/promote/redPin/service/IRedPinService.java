package com.autuan.project.promote.redPin.service;

import com.autuan.project.promote.redPin.domain.RedPin;
import java.util.List;

/**
 * 红包码信息Service接口
 * 
 * @author autuan
 * @date 2020-09-09
 */
public interface IRedPinService 
{
    /**
     * 查询红包码信息
     * 
     * @param id 红包码信息ID
     * @return 红包码信息
     */
    public RedPin selectRedPinById(String id);

    /**
     * 查询红包码信息列表
     * 
     * @param redPin 红包码信息
     * @return 红包码信息集合
     */
    public List<RedPin> selectRedPinList(RedPin redPin);

    /**
     * 新增红包码信息
     * 
     * @param redPin 红包码信息
     * @return 结果
     */
    public int insertRedPin(RedPin redPin);

    /**
     * 修改红包码信息
     * 
     * @param redPin 红包码信息
     * @return 结果
     */
    public int updateRedPin(RedPin redPin);

    /**
     * 批量删除红包码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRedPinByIds(String ids);

    /**
     * 删除红包码信息信息
     * 
     * @param id 红包码信息ID
     * @return 结果
     */
    public int deleteRedPinById(String id);
}
