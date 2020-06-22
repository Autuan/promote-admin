package com.autuan.project.promote.group.service.impl;

import cn.hutool.core.util.IdUtil;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.group.domain.TabGroup;
import com.autuan.project.promote.group.domain.TabGroupExample;
import com.autuan.project.promote.group.mapper.TabGroupMapper;
import com.autuan.project.promote.group.service.IGroupCustomService;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.domain.TabSalesmanExample;
import com.autuan.project.promote.salesman.mapper.TabSalesmanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/22 15:14
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class GroupCustomServiceImpl implements IGroupCustomService {
    @Autowired
    private TabGroupMapper groupMapper;
    @Autowired
    private TabSalesmanMapper salesmanMapper;
    /**
     * 查询是否已是组长
     *
     * @throws
     * @author : Autuan.Yu
     * @return: boolean
     * @since : 2020/6/22 15:15
     */
    @Override
    public boolean haveThisHeader(String salesmanId) {
        TabGroupExample example = new TabGroupExample();
        example.createCriteria()
                .andGroupHeaderEqualTo(salesmanId);
        return groupMapper.selectByExample(example).size() > 0;
    }

    /**
     * 是否已加入小组
     *
     * @param salesmanId
     * @throws
     * @author : Autuan.Yu
     * @return: boolean
     * @since : 2020/6/22 15:35
     */
    @Override
    public boolean joinedGroup(String salesmanId) {
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
                .andGroupIdIsNotNull()
                .andIdEqualTo(salesmanId);
        return salesmanMapper.selectByExample(example).size() > 0;
    }

    /**
     * 添加小组
     *
     * @param group
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/22 15:22
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(TabGroup group) {
        if(haveThisHeader(group.getGroupHeader())) {
            throw new CustomRespondException("此销售员已是组长");
        }
        if(joinedGroup(group.getGroupHeader())) {
            throw new CustomRespondException("此销售员已有小组");
        }
        group.setCreateTime(LocalDateTime.now());
        group.setCreateBy(ShiroUtils.getLoginName());
        String groupId = IdUtil.simpleUUID();
        group.setId(groupId);
        groupMapper.insertSelective(group);
        salesmanMapper.updateByPrimaryKeySelective(
                TabSalesman.builder()
                .id(group.getGroupHeader())
                .groupId(groupId)
                .build());
    }

    /**
     * 修改小组
     *
     * @param group
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/22 15:39
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(TabGroup group) {
        TabGroupExample example = new TabGroupExample();
        example.createCriteria()
                .andIdNotEqualTo(group.getId())
                .andGroupHeaderEqualTo(group.getGroupHeader());
        boolean haveThisHeaderBool = groupMapper.selectByExample(example).size() > 0;
        if(haveThisHeaderBool) {
            throw new CustomRespondException("此销售员已是组长");
        }
        TabSalesmanExample salesmanExample = new TabSalesmanExample();
        salesmanExample.createCriteria()
                .andGroupIdIsNotNull()
                .andGroupIdNotEqualTo(group.getId())
                .andIdEqualTo(group.getGroupHeader());
        boolean joinedBool = salesmanMapper.selectByExample(salesmanExample).size() > 0;
        if(joinedGroup(group.getGroupHeader())) {
            throw new CustomRespondException("此销售员已有小组");
        }
        groupMapper.updateByPrimaryKeySelective(group);
        salesmanMapper.updateByPrimaryKeySelective(
                TabSalesman.builder()
                        .id(group.getGroupHeader())
                        .groupId(group.getId())
                        .build());
    }

    /**
     * 获取列表集合
     *
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.group.domain.TabGroup>
     * @since : 2020/6/22 16:24
     */
    @Override
    public List<TabGroup> list() {
        return groupMapper.selectByExample(null);
    }
}
