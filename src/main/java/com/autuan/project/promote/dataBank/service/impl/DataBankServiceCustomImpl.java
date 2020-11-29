package com.autuan.project.promote.dataBank.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.mapper.TabDataBankMapper;
import com.autuan.project.promote.dataBank.service.IDataBankCustomService;
import com.autuan.project.promote.dataBank.service.IDataBankService;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.salesman.domain.TabSalesmanExample;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import com.autuan.project.promote.task.domain.TaskEnum;
import com.autuan.project.promote.task.mapper.TabTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.autuan.project.promote.base.constant.Constant.YES;

/**
 * @className: DataBankServiceCustomImpl
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/25 20:54
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Service
@Slf4j
public class DataBankServiceCustomImpl implements IDataBankCustomService {
    @Autowired
    private TabDataBankMapper dataBankMapper;
    @Autowired
    private IDataBankService dataBankService;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    @Autowired
    private TabTaskMapper tabTaskMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importExcel(List<TabDataBank> list) {
        StringBuilder strBuilder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();

        // 使用code 和 name 分别查出 taskId 和 salesmanId
        // taskId
        TabTaskExample tabTaskExample = new TabTaskExample();
        for (TabDataBank dataBank : list) {
            tabTaskExample.or()
                    .andIndexNameEqualTo(dataBank.getBankName());
        }
        List<TabTask> taskList = tabTaskMapper.selectByExample(tabTaskExample);
        Map<String, TabTask> taskMap = taskList.stream()
                .collect(Collectors.toMap(TabTask::getIndexName, Function.identity(), (existing, replacement) -> existing));
        // salesmanId
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        for (TabDataBank dataBank : list) {

            TabTask task = taskMap.get(dataBank.getBankName());
            if (null != task) {
                String taskId = task.getId();
                salesmanTaskExample.or()
                        .andStatusEqualTo(TaskEnum.STATUS_PASS.val())
                        .andSalesmanIdIsNotNull()
                        .andCodeEqualTo(dataBank.getChannelCode())
                        .andTaskIdEqualTo(taskId);
            }
        }
        List<TabSalesmanTask> tabSalesmanTaskList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(salesmanTaskExample.getOredCriteria())) {
            tabSalesmanTaskList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        }
        Map<String, String> linkMap = tabSalesmanTaskList.stream()
                .filter(item -> StrUtil.isNotBlank(item.getCode()))
                .filter(item -> StrUtil.isNotBlank(item.getSalesmanId()))
                .collect(Collectors.toMap(
                        item-> item.getCode()+"-"+item.getTaskId(),
                        TabSalesmanTask::getSalesmanId ));
        // 插入
        List<TabDataBank> insertList = new ArrayList<>();
        int line = 0;
        int errorNum = 0;
        for (TabDataBank dataBank : list) {
            line++;
            TabTask task = taskMap.get(dataBank.getBankName());
            if (null == task) {
                strBuilder.append("<br/> 第" + line + "条数据未导入： 未查询到任务.  任务索引名:" + dataBank.getBankName());
                errorNum++;
                continue;
            }
            String salesmanId = linkMap.get(dataBank.getChannelCode()+"-"+task.getId());
            if (StrUtil.isBlank(salesmanId)) {
                strBuilder.append("<br/> 第" + line + "条数据未导入： 未查询到业务员.  业务员CODE:" + dataBank.getChannelCode());
                errorNum++;
                continue;
            }
            String taskId = task.getId();
            dataBank.setTaskId(taskId);
            dataBank.setSalesmanId(salesmanId);
            dataBank.setCreateTime(now);
            dataBank.setCreateBy(loginName);
            dataBank.setId(IdUtil.simpleUUID());
            // 只有符合条件的置入奖励值
            Integer customFlag = dataBank.getCustomFlag();
            Integer approveStatus = dataBank.getApproveStatus();
            if (customFlag.equals(YES) && approveStatus.equals(YES)) {
                dataBank.setReward(task.getReward());
            } else {
                dataBank.setReward(BigDecimal.ZERO);
            }
            insertList.add(dataBank);
        }
        if (CollectionUtil.isNotEmpty(insertList)) {
            dataBankMapper.batchInsert(insertList);
        }

        strBuilder.insert(0, "共 " + list.size() + " 条数据,成功：" + (list.size() - errorNum) + " 失败: " + errorNum);
        return strBuilder.toString();
    }

    @Override
    public String importData(List<DataBank> list, Boolean isUpdateSupport) {
        if (CollectionUtil.isEmpty(list)) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (DataBank dataBank : list) {
            try {
                DataBank data = null;
                if (null == data) {
                    dataBankService.insertDataBank(dataBank);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + dataBank.getCName() + " 导入成功");
                } else if (isUpdateSupport) {
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + dataBank.getCName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + dataBank.getCName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + dataBank.getCName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}