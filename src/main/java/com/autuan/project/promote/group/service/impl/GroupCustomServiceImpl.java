package com.autuan.project.promote.group.service.impl;

import cn.hutool.core.util.IdUtil;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.front.entity.*;
import com.autuan.project.promote.group.domain.TabGroup;
import com.autuan.project.promote.group.domain.TabGroupExample;
import com.autuan.project.promote.group.mapper.TabGroupMapper;
import com.autuan.project.promote.group.service.IGroupCustomService;
import com.autuan.project.promote.salesman.domain.CalcuRewardRes;
import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.domain.TabSalesmanExample;
import com.autuan.project.promote.salesman.mapper.TabSalesmanMapper;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private ISalesmanCustomService salesmanCustomService;
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

    @Override
    public List<GroupDataRes> groupData(GroupDataReq req) {
        // 查出当前小组信息
        TabGroupExample groupExample = new TabGroupExample();
        groupExample.createCriteria()
                .andIdEqualTo(req.getGroupId());
        TabGroup group = groupMapper.selectOneByExample(groupExample);

        // 查出当前小组所有成员
        TabSalesmanExample salesmanExample = new TabSalesmanExample();
        salesmanExample.createCriteria()
                .andGroupIdEqualTo(req.getGroupId());
        List<TabSalesman> salesmanList = salesmanMapper.selectByExample(salesmanExample);

        // 查出每个成员业绩
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createTime = group.getCreateTime();
        List<LocalDateTime> queryDateList = new ArrayList<>();
        int monthDiff = monthDiff(createTime, now);
        for(int i=0;i<=monthDiff;i++) {
            LocalDateTime queryDate = createTime.plusMonths(i);
            queryDateList.add(queryDate);
        }

        List<GroupDataRes> resList = new ArrayList<>();
        for(TabSalesman salesman : salesmanList) {
            String salesmanId = salesman.getId();
            List<GroupDataDetailDTO> detailList = new ArrayList<>();
            for(LocalDateTime queryDate : queryDateList) {
                CalcuRewardReq reqBean = CalcuRewardReq.builder()
                        .queryMoon(queryDate)
                        .salesmanId(salesmanId)
                        .build();
                CalcuRewardRes calcuRewardRes = salesmanCustomService.calcuReward(reqBean);
                GroupDataDetailDTO dto = GroupDataDetailDTO.builder()
.count(calcuRewardRes.getThisMoonReward())
                        .date(queryDate)
                        .id(queryDate.toString())
                        .build();
                detailList.add(dto);
            }

            GroupDataRes dataRes = GroupDataRes.builder()
                    .salesmanId(salesmanId)
                    .salesmanName(salesman.getName())
                    .detail(detailList)
                    .build();
            resList.add(dataRes);
        }
        // 计算小组业绩 todo 前台计算

        return resList;
    }

    /**
     * 获取两个时间点的月份差
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即需求的月数差
     */
    private int monthDiff(LocalDateTime dt1,LocalDateTime dt2){
        //获取第一个时间点的月份
        int month1 = dt1.getMonthValue();
        //获取第一个时间点的年份
        int year1 = dt1.getYear();
        //获取第一个时间点的月份
        int month2 = dt2.getMonthValue();
        //获取第一个时间点的年份
        int year2 = dt2.getYear();
        //返回两个时间点的月数差
        return (year2 - year1) *12 + (month2 - month1);
    }
}
