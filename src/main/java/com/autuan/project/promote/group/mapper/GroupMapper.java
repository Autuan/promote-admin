package com.autuan.project.promote.group.mapper;

import com.autuan.project.promote.group.domain.Group;
import java.util.List;

/**
 * 小组Mapper接口
 * 
 * @author autuan
 * @date 2020-06-22
 */
public interface GroupMapper 
{
    /**
     * 查询小组
     * 
     * @param id 小组ID
     * @return 小组
     */
    public Group selectGroupById(String id);

    /**
     * 查询小组列表
     * 
     * @param group 小组
     * @return 小组集合
     */
    public List<Group> selectGroupList(Group group);

    /**
     * 新增小组
     * 
     * @param group 小组
     * @return 结果
     */
    public int insertGroup(Group group);

    /**
     * 修改小组
     * 
     * @param group 小组
     * @return 结果
     */
    public int updateGroup(Group group);

    /**
     * 删除小组
     * 
     * @param id 小组ID
     * @return 结果
     */
    public int deleteGroupById(String id);

    /**
     * 批量删除小组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGroupByIds(String[] ids);
}
