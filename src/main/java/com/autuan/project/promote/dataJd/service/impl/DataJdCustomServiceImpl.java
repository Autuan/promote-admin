package com.autuan.project.promote.dataJd.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.dataJd.domain.OptionJdRewardReq;
import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.mapper.TabDataJdMapper;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import com.autuan.project.promote.task.mapper.TabTaskMapper;
import com.autuan.project.system.dict.domain.DictData;
import com.autuan.project.system.dict.domain.DictType;
import com.autuan.project.system.dict.service.IDictDataService;
import com.autuan.project.system.dict.service.IDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/7/1 11:13
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class DataJdCustomServiceImpl implements IDataJdCustomService {
    @Autowired
    private TabDataJdMapper dataJdMapper;
    @Autowired
    private TabTaskMapper tabTaskMapper;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private IDictTypeService dictTypeService;
    /**
     * 批量导入
     *
     * @param list
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/7/1 11:13
     */
    @Override
    public void importExcel(List<TabDataJd> list) {
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();

//        BigDecimal rewardNewbie = new BigDecimal(6);
//        BigDecimal rewardCommon = new BigDecimal(28);
//        BigDecimal rewardGold = new BigDecimal(8);
        // 使用code 和 name 分别查出 taskId 和 salesmanId
        // taskId
        TabTaskExample tabTaskExample = new TabTaskExample();
        for (TabDataJd data : list) {
            tabTaskExample.or()
                    .andIndexNameEqualTo(data.getOrderName());
        }
        List<TabTask> taskList = tabTaskMapper.selectByExample(tabTaskExample);
        Map<String, String> taskMap = taskList.stream()
                .collect(Collectors.toMap(TabTask::getName, TabTask::getId));
        // salesmanId
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        for (TabDataJd data : list) {
            String taskId = taskMap.get(data.getOrderName());
            if(StrUtil.isNotBlank(taskId)) {
                salesmanTaskExample.or()
                        .andCodeEqualTo(data.getJoinLink())
                        .andTaskIdEqualTo(taskId);
            }
        }
        List<TabSalesmanTask> tabSalesmanTaskList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(salesmanTaskExample.getOredCriteria())) {
            tabSalesmanTaskList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        }
        Map<String, String> linkMap = tabSalesmanTaskList.stream()
                .collect(Collectors.toMap(TabSalesmanTask::getCode, TabSalesmanTask::getSalesmanId));

        List<TabDataJd> insertList = new ArrayList<>();
        for (TabDataJd data : list) {
            String taskId = taskMap.get(data.getOrderName());
            String salesmanId = linkMap.get(data.getJoinLink());

            List<DictData> dictDatas = dictDataService.selectDictDataByType("JD_REWARD_" + taskId);
            String rewardNewbieStr = dictDatas.stream().filter(item -> "新手礼包".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().orElse("0");
            String rewardCommonStr = dictDatas.stream().filter(item -> "京东白条".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().orElse("0");
            String rewardGoldStr = dictDatas.stream().filter(item -> "京东金条".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().orElse("0");
            BigDecimal rewardNewbie = new BigDecimal(rewardNewbieStr);
            BigDecimal rewardCommon = new BigDecimal(rewardCommonStr);
            BigDecimal rewardGold = new BigDecimal(rewardGoldStr);

            if(StrUtil.isBlank(taskId) || StrUtil.isBlank(salesmanId)) {
                continue;
            }
            data.setTaskId(taskId);
            data.setSalesmanId(salesmanId);
            data.setCreateTime(now);
            data.setCreateBy(loginName);
            data.setId(IdUtil.simpleUUID());
            // 根据类型计算reward
            Integer type = data.getOpenJdCreditType();
            BigDecimal reward = rewardCommon;
            switch (type){
                case 0 : reward =rewardCommon;break;
                case 1 : reward =rewardGold;break;
                case 2 : reward =rewardNewbie;break;
            }
            data.setReward(reward);
            insertList.add(data);
        }
        if(CollectionUtil.isNotEmpty(insertList)) {
            dataJdMapper.batchInsert(insertList);
        }
    }

    @Override
    public void optionJdReward(OptionJdRewardReq req) {
        // todo 魔法值
//        List<DictData> dictData = dictDataService.selectDictDataByType("jd_reward_num");

        String dictName = "JD_REWARD_" + req.getTaskId();

        DictType dictType = new DictType();
        dictType.setDictName(dictName);
        dictType.setDictType(dictName);
        dictType.setStatus("0");
        dictTypeService.insertDictType(dictType);

        DictData newbieDictData = new DictData();
//        dictData.setDictCode(dictType.getDictId());
        newbieDictData.setDictLabel("新手礼包");
        newbieDictData.setDictValue(req.getRewardNewbie().toString());
        newbieDictData.setDictType(dictName);
        newbieDictData.setIsDefault("N");
        newbieDictData.setStatus("0");
        dictDataService.insertDictData(newbieDictData);

        DictData commonDictData = new DictData();
//        dictData.setDictCode(dictType.getDictId());
        commonDictData.setDictLabel("京东白条");
        commonDictData.setDictValue(req.getRewardCommon().toString());
        commonDictData.setDictType(dictName);
        commonDictData.setIsDefault("N");
        commonDictData.setStatus("0");
        dictDataService.insertDictData(commonDictData);

        DictData goldDictData = new DictData();
//        dictData.setDictCode(dictType.getDictId());
        goldDictData.setDictLabel("京东金条");
        goldDictData.setDictValue(req.getRewardGold().toString());
        goldDictData.setDictType(dictName);
        goldDictData.setIsDefault("N");
        goldDictData.setStatus("0");

        dictDataService.insertDictData(goldDictData);


    }
}
