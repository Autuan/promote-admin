package com.autuan.project.promote.task.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.task.mapper.TaskMapper;
import com.autuan.project.promote.task.domain.Task;
import com.autuan.project.promote.task.service.ITaskService;
import com.autuan.common.utils.text.Convert;

/**
 * 任务Service业务层处理
 *
 * @author autuan
 * @date 2020-07-04
 */
@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询任务
     *
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public Task selectTaskById(String id) {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询任务列表
     *
     * @param task 任务
     * @return 任务
     */
    @Override
    public List<Task> selectTaskList(Task task) {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增任务
     *
     * @param task 任务
     * @return 结果
     */
    @Override
    public int insertTask(Task task) {
        task.setCreateTime(LocalDateTime.now());
        task.setCreateBy(ShiroUtils.getLoginName());
        task.setId(IdUtil.simpleUUID());
        return taskMapper.insertTask(task);
    }

    /**
     * 修改任务
     *
     * @param task 任务
     * @return 结果
     */
    @Override
    public int updateTask(Task task) {
        task.setUpdateTime(LocalDateTime.now());
        task.setUpdateBy(ShiroUtils.getLoginName());
        return taskMapper.updateTask(task);
    }

    /**
     * 删除任务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(String ids) {
        return taskMapper.deleteTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务信息
     *
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteTaskById(String id) {
        return taskMapper.deleteTaskById(id);
    }
}
