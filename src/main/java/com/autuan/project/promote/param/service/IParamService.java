package com.autuan.project.promote.param.service;

import com.autuan.project.promote.param.domain.Param;
import java.util.List;

/**
 * 任务链接参数Service接口
 * 
 * @author autuan
 * @date 2020-06-23
 */
public interface IParamService 
{
    /**
     * 查询任务链接参数
     * 
     * @param id 任务链接参数ID
     * @return 任务链接参数
     */
    public Param selectParamById(String id);

    /**
     * 查询任务链接参数列表
     * 
     * @param param 任务链接参数
     * @return 任务链接参数集合
     */
    public List<Param> selectParamList(Param param);

    /**
     * 新增任务链接参数
     * 
     * @param param 任务链接参数
     * @return 结果
     */
    public int insertParam(Param param);

    /**
     * 修改任务链接参数
     * 
     * @param param 任务链接参数
     * @return 结果
     */
    public int updateParam(Param param);

    /**
     * 批量删除任务链接参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteParamByIds(String ids);

    /**
     * 删除任务链接参数信息
     * 
     * @param id 任务链接参数ID
     * @return 结果
     */
    public int deleteParamById(String id);
}
