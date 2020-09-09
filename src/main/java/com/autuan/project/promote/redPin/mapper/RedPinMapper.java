package com.autuan.project.promote.redPin.mapper;

import com.autuan.project.promote.redPin.domain.RedPin;
import java.util.List;

/**
 * 红包码京东和业务员关联Mapper接口
 * 
 * @author autuan
 * @date 2020-09-09
 */
public interface RedPinMapper 
{
    /**
     * 查询红包码京东和业务员关联
     * 
     * @param id 红包码京东和业务员关联ID
     * @return 红包码京东和业务员关联
     */
    public RedPin selectRedPinById(String id);

    /**
     * 查询红包码京东和业务员关联列表
     * 
     * @param redPin 红包码京东和业务员关联
     * @return 红包码京东和业务员关联集合
     */
    public List<RedPin> selectRedPinList(RedPin redPin);

    /**
     * 新增红包码京东和业务员关联
     * 
     * @param redPin 红包码京东和业务员关联
     * @return 结果
     */
    public int insertRedPin(RedPin redPin);

    /**
     * 修改红包码京东和业务员关联
     * 
     * @param redPin 红包码京东和业务员关联
     * @return 结果
     */
    public int updateRedPin(RedPin redPin);

    /**
     * 删除红包码京东和业务员关联
     * 
     * @param id 红包码京东和业务员关联ID
     * @return 结果
     */
    public int deleteRedPinById(String id);

    /**
     * 批量删除红包码京东和业务员关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRedPinByIds(String[] ids);
}
