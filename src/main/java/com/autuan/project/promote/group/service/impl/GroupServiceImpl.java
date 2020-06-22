package com.autuan.project.promote.group.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.group.domain.TabGroupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.group.mapper.GroupMapper;
import com.autuan.project.promote.group.domain.Group;
import com.autuan.project.promote.group.service.IGroupService;
import com.autuan.common.utils.text.Convert;

/**
 * 小组Service业务层处理
 *
 * @author autuan
 * @date 2020-06-22
 */
@Service
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private GroupMapper groupMapper;

    /**
     * 查询小组
     *
     * @param id 小组ID
     * @return 小组
     */
    @Override
    public Group selectGroupById(String id) {
        return groupMapper.selectGroupById(id);
    }

    /**
     * 查询小组列表
     *
     * @param group 小组
     * @return 小组
     */
    @Override
    public List<Group> selectGroupList(Group group) {
        return groupMapper.selectGroupList(group);
    }

    /**
     * 新增小组
     *
     * @param group 小组
     * @return 结果
     */
    @Override
    public int insertGroup(Group group) {
        group.setCreateTime(LocalDateTime.now());
        group.setCreateBy(ShiroUtils.getLoginName());
        group.setId(IdUtil.simpleUUID());
        return groupMapper.insertGroup(group);
    }

    /**
     * 修改小组
     *
     * @param group 小组
     * @return 结果
     */
    @Override
    public int updateGroup(Group group) {
        group.setUpdateTime(LocalDateTime.now());
        group.setUpdateBy(ShiroUtils.getLoginName());
        return groupMapper.updateGroup(group);
    }

    /**
     * 删除小组对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGroupByIds(String ids) {
        return groupMapper.deleteGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除小组信息
     *
     * @param id 小组ID
     * @return 结果
     */
    @Override
    public int deleteGroupById(String id) {
        return groupMapper.deleteGroupById(id);
    }
}
