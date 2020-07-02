package com.autuan.project.promote.link.linkSalesmanTask.mapper;

import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import java.util.List;

/**
 * 业务员-任务中间Mapper接口
 * 
 * @author autuan
 * @date 2020-07-02
 */
public interface SalesmanTaskMapper 
{
    /**
     * 查询业务员-任务中间
     * 
     * @param id 业务员-任务中间ID
     * @return 业务员-任务中间
     */
    public SalesmanTask selectSalesmanTaskById(String id);

    /**
     * 查询业务员-任务中间列表
     * 
     * @param salesmanTask 业务员-任务中间
     * @return 业务员-任务中间集合
     */
    public List<SalesmanTask> selectSalesmanTaskList(SalesmanTask salesmanTask);

    /**
     * 新增业务员-任务中间
     * 
     * @param salesmanTask 业务员-任务中间
     * @return 结果
     */
    public int insertSalesmanTask(SalesmanTask salesmanTask);

    /**
     * 修改业务员-任务中间
     * 
     * @param salesmanTask 业务员-任务中间
     * @return 结果
     */
    public int updateSalesmanTask(SalesmanTask salesmanTask);

    /**
     * 删除业务员-任务中间
     * 
     * @param id 业务员-任务中间ID
     * @return 结果
     */
    public int deleteSalesmanTaskById(String id);

    /**
     * 批量删除业务员-任务中间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesmanTaskByIds(String[] ids);
}
