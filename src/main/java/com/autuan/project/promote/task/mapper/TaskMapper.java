package com.autuan.project.promote.task.mapper;

import com.autuan.project.promote.task.domain.Task;
import java.util.List;

/**
 * 任务Mapper接口
 * 
 * @author autuan
 * @date 2020-07-04
 */
public interface TaskMapper 
{
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    public Task selectTaskById(String id);

    /**
     * 查询任务列表
     * 
     * @param task 任务
     * @return 任务集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改任务
     * 
     * @param task 任务
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 删除任务
     * 
     * @param id 任务ID
     * @return 结果
     */
    public int deleteTaskById(String id);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskByIds(String[] ids);
}
