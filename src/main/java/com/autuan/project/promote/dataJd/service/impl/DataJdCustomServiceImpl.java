package com.autuan.project.promote.dataJd.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.promote.dataJd.domain.OptionJdRewardReq;
import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.domain.TabDataJdExample;
import com.autuan.project.promote.dataJd.mapper.TabDataJdMapper;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import com.autuan.project.promote.task.domain.TaskEnum;
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
import java.util.*;
import java.util.stream.Collectors;

import static com.autuan.project.promote.base.constant.Constant.*;

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
    public String importExcel(List<TabDataJd> list) {
        StringBuilder strBuilder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();
        // 使用code 和 name 分别查出 taskId 和 salesmanId
        // taskId
        TabTaskExample tabTaskExample = new TabTaskExample();
        for (TabDataJd data : list) {
            tabTaskExample.or()
                    .andIndexNameEqualTo(data.getOrderName());
        }
        List<TabTask> taskList = tabTaskMapper.selectByExample(tabTaskExample);
        Map<String, String> taskMap = taskList.stream()
                .collect(Collectors.toMap(TabTask::getIndexName, TabTask::getId,(existing, replacement) -> existing));
        // salesmanId
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        for (TabDataJd data : list) {
            String taskId = taskMap.get(data.getOrderName());
            if(StrUtil.isNotBlank(taskId)) {
                salesmanTaskExample.or()
                        .andStatusEqualTo(TaskEnum.STATUS_PASS.val())
                        .andSalesmanIdIsNotNull()
                        .andCodeEqualTo(data.getJoinLink())
                        .andTaskIdEqualTo(taskId);
            }
        }
        List<TabSalesmanTask> tabSalesmanTaskList = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(salesmanTaskExample.getOredCriteria())) {
            tabSalesmanTaskList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        }
        Map<String, String> linkMap = tabSalesmanTaskList.stream()
                .collect(Collectors.toMap(
                        item-> item.getCode()+"-"+item.getTaskId(),
                        TabSalesmanTask::getSalesmanId));

        List<TabDataJd> insertList = new ArrayList<>();
        int line = 0;
        int errorNum = 0;
        for (TabDataJd data : list) {
            line++;
            String taskId = taskMap.get(data.getOrderName());
            if(StrUtil.isBlank(taskId) ) {
                strBuilder.append("<br/> 第" + line + "条数据未导入： 未查询到任务.  任务索引名:" + data.getOrderName());
                errorNum++;
                continue;
            }
            String salesmanId = linkMap.get(data.getJoinLink()+"-"+taskId);

            if( StrUtil.isBlank(salesmanId)) {
                strBuilder.append("<br/> 第" + line + "条数据未导入： 未查询到业务员.  业务员CODE:" + data.getJoinLink());
                errorNum++;
                continue;
            }

            List<DictData> dictDatas = dictDataService.selectDictDataByType("JD_REWARD_" + taskId);
            BigDecimal rewardNewbie = dictDatas.stream().filter(item -> "新手礼包".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().map(BigDecimal::new).orElse(BigDecimal.ZERO);
            BigDecimal rewardCommon = dictDatas.stream().filter(item -> "京东白条".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().map(BigDecimal::new).orElse(BigDecimal.ZERO);
            BigDecimal rewardGold = dictDatas.stream().filter(item -> "京东金条".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().map(BigDecimal::new).orElse(BigDecimal.ZERO);

            data.setTaskId(taskId);
            data.setSalesmanId(salesmanId);
            data.setCreateTime(now);
            data.setCreateBy(loginName);
            data.setId(IdUtil.simpleUUID());
            // 根据类型计算reward
            Integer type = data.getOpenJdCreditType();
            BigDecimal reward = rewardCommon;
            switch (type){
                case JD_TYPE_COMMON : reward =rewardCommon;break;
                case JD_TYPE_GOLD : reward =rewardGold;break;
                case JD_TYPE_NEWBIE : reward =rewardNewbie;break;
                default:break;
            }
            data.setReward(reward);
            insertList.add(data);
        }
        if(CollectionUtil.isNotEmpty(insertList)) {
            dataJdMapper.batchInsert(insertList);
        }
        strBuilder.insert(0, "共 " + list.size() + " 条数据,成功：" + (list.size() - errorNum) + " 失败: " + errorNum);
        return strBuilder.toString();
    }

    @Override
    public void optionJdReward(OptionJdRewardReq req) {
        // todo magic val
        String dictName = "JD_REWARD_" + req.getTaskId();

        DictType dictType = new DictType();
        dictType.setDictName(dictName);
        dictType.setDictType(dictName);
        dictType.setStatus("0");
        dictTypeService.insertDictType(dictType);

        DictData newbieDictData = new DictData();
        newbieDictData.setDictLabel("新手礼包");
        newbieDictData.setDictValue(req.getRewardNewbie().toString());
        newbieDictData.setDictType(dictName);
        newbieDictData.setIsDefault("N");
        newbieDictData.setStatus("0");
        dictDataService.insertDictData(newbieDictData);

        DictData commonDictData = new DictData();
        commonDictData.setDictLabel("京东白条");
        commonDictData.setDictValue(req.getRewardCommon().toString());
        commonDictData.setDictType(dictName);
        commonDictData.setIsDefault("N");
        commonDictData.setStatus("0");
        dictDataService.insertDictData(commonDictData);

        DictData goldDictData = new DictData();
        goldDictData.setDictLabel("京东金条");
        goldDictData.setDictValue(req.getRewardGold().toString());
        goldDictData.setDictType(dictName);
        goldDictData.setIsDefault("N");
        goldDictData.setStatus("0");

        dictDataService.insertDictData(goldDictData);
    }

    @Override
    public Map<String, BigDecimal> getRewardOption(Set<String> ids) {
        Map<String,BigDecimal> result = new HashMap<>(ids.size() * 3);
        for(String taskId : ids) {
            // todo magic val
            List<DictData> dictDatas = dictDataService.selectDictDataByType("JD_REWARD_" + taskId);
            BigDecimal rewardNewbie = dictDatas.stream().filter(item -> "新手礼包".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().map(BigDecimal::new).orElse(BigDecimal.ZERO);
            BigDecimal rewardCommon = dictDatas.stream().filter(item -> "京东白条".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().map(BigDecimal::new).orElse(BigDecimal.ZERO);
            BigDecimal rewardGold = dictDatas.stream().filter(item -> "京东金条".equals(item.getDictLabel()))
                    .map(DictData::getDictValue)
                    .findFirst().map(BigDecimal::new).orElse(BigDecimal.ZERO);

            result.put("rewardNewbie"+taskId,rewardNewbie);
            result.put("rewardCommon"+taskId,rewardCommon);
            result.put("rewardGold"+taskId,rewardGold);
        }
        return result;
    }

    /**
     * 按月查询数据
     *
     * @param req
     * @throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.front.entity.HistoryRewardRes>
     * @since : 2020/7/5 13:27
     */
    @Override
    public List<HistoryRewardRes> jdList(HistoryRewardReq req) {
        String salesmanId = req.getSalesmanId();
        String[] dateStrArray = req.getQueryDateStr().split("-");
        LocalDateTime startTime = LocalDateTime.of(Integer.parseInt(dateStrArray[0]), Integer.parseInt(dateStrArray[1]), 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getMonth().maxLength(), 23, 59, 59);

        // 开卡订单
        TabDataJdExample example = new TabDataJdExample();
        example.createCriteria()
                .andTaskIdIsNotNull()
                .andJoinLinkIsNotNull()
                .andSalesmanIdEqualTo(salesmanId)
                .andOpenJdCreditTimeBetween(startTime, endTime);
        List<TabDataJd> dataJds = dataJdMapper.selectByExample(example);

        Set<String> taskIdSet = dataJds.stream()
                .map(TabDataJd::getTaskId)
                .collect(Collectors.toSet());
        Map<String, BigDecimal> rewardOption = getRewardOption(taskIdSet);

        List<HistoryRewardRes> resList = new ArrayList<>();
        for(TabDataJd data : dataJds){
            String typeStr = "";
            resList.add(HistoryRewardRes.builder()
                    .verifyDate(data.getOpenJdCreditTime())
                    .name(data.getOrderName())
                    .orderNo(data.getOpenJdCreditPin())
                    .reward(data.getReward())
                    .type(typeStr)
                    .build());
        }
        return resList;
    }
}
