package com.autuan.project.promote.salesman.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.Md5Utils;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.text.Convert;
import com.autuan.project.front.entity.CalcuRewardReq;
import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBankExample;
import com.autuan.project.promote.dataBank.mapper.TabDataBankMapper;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.TabSalesmanTaskMapper;
import com.autuan.project.promote.salesman.domain.*;
import com.autuan.project.promote.salesman.mapper.SalesmanMapper;
import com.autuan.project.promote.salesman.mapper.TabSalesmanMapper;
import com.autuan.project.promote.salesman.service.ISalesmanCustomService;
import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import com.autuan.project.promote.task.mapper.TabTaskMapper;
import com.autuan.project.system.dict.domain.DictData;
import com.autuan.project.system.dict.service.IDictDataService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/18 16:42
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class SalesmanCustomServiceImpl implements ISalesmanCustomService {
    @Autowired
    private TabSalesmanMapper salesmanMapper;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    @Autowired
    private TabTaskMapper taskMapper;
    @Autowired
    private TabDataBankMapper dataBankMapper;
    @Autowired
    private IDictDataService dictDataService;

    /**
     * 登录方法(返回用户信息)
     *
     * @param salesman mobile:手机号 password:密码
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.Salesman
     * @since : 2020/6/18 16:44
     */
    @Override
    public TabSalesman login(TabSalesman salesman) {
//        String pwd = Md5Utils.hash(salesman.getPassword());
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
                .andMobileEqualTo(salesman.getMobile())
//                .andPasswordEqualTo(pwd)
                .andPasswordEqualTo(salesman.getPassword());
        TabSalesman tabSalesman = salesmanMapper.selectOneByExample(example);
        return tabSalesman;
    }

    /**
     * 注册用户
     *
     * @param salesman
     * @throws
     * @author : Autuan.Yu
     * @return: com.autuan.project.promote.salesman.domain.TabSalesman
     * @since : 2020/6/19 15:24
     */
    @Override
    public boolean register(TabSalesman salesman) {
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
                .andMobileEqualTo(salesman.getMobile());
        TabSalesman one = salesmanMapper.selectOneByExample(example);
        if (null != one && StrUtil.isNotBlank(one.getId())) {
            throw new CustomRespondException("此手机号已注册,请直接登录");
        }
        salesman.setCreateTime(LocalDateTime.now());
        salesman.setCreateBy("用户注册");
        salesman.setId(IdUtil.simpleUUID());
        salesman.setLevel("普通用户");
        return salesmanMapper.insertSelective(salesman) == 1;
    }

    @Override
    public TabSalesman selectByMobile(String mobile) {
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
//                .andMobileLike("%"+mobile+"%")
                .andMobileEqualTo(mobile)
        ;
        TabSalesman tabSalesman = salesmanMapper.selectOneByExample(example);
        log.info("selectByMobile -> response -> {}", tabSalesman);
        return tabSalesman;
    }

    /**
     * 重置密码
     *
     * @param ids
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/22 16:51
     */
    @Override
    public void resetPwd(String ids) {
        List<String> idList = Arrays.asList(Convert.toStrArray(ids));
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
                .andIdIn(idList);
        TabSalesman salesman =
                TabSalesman.builder()
                        .password(Md5Utils.hash("123456"))
                        .build();
        salesmanMapper.updateByExampleSelective(salesman, example);
    }

    /**
     * 修改密码
     *
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/29 11:15
     */
    @Override
    public void updatePwd(TabSalesman salesman) {
        salesmanMapper.updateByPrimaryKeySelective(salesman);
    }

    @Override
    public List<TabSalesman> listSalesmanThousand() {
        TabSalesmanExample example = new TabSalesmanExample();
        example.limit(1000);
        return salesmanMapper.selectByExample(example);
    }

    /**
     * 计算收益
     *
     * @throws
     * @author : Autuan.Yu
     * @return: java.lang.Object
     * @since : 2020/6/29 14:04
     */
    @Override
    public CalcuRewardRes calcuReward(CalcuRewardReq req) {
        String salesmanId = req.getSalesmanId();
        // 该业务员领取的所有任务
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        salesmanTaskExample.createCriteria()
                .andSalesmanIdEqualTo(salesmanId);
        List<TabSalesmanTask> receiveList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        if (CollectionUtil.isEmpty(receiveList)) {
            return CalcuRewardRes.zero();
        }
        // 所有业务
        TabTaskExample taskExample = new TabTaskExample();
        taskExample.createCriteria()
                .andIdIn(receiveList.stream().map(TabSalesmanTask::getTaskId).collect(toList()));
        List<TabTask> allTasks = taskMapper.selectByExample(taskExample);
        // 所有通过的开卡订单
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIn(allTasks.stream().map(TabTask::getId).collect(toList()))
                .andSalesmanIdEqualTo(salesmanId)
                // 1:是
                .andCustomFlagEqualTo(1)
                // 1:通过
                .andApproveStatusEqualTo(1);
        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);

        // 京东推广 todo

        // 本月预估推广费
        BigDecimal thisMoonReward = BigDecimal.ZERO;
        // 累计推广费
        BigDecimal historyReward = BigDecimal.ZERO;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime queryMoon = req.getQueryMoon() == null ? now : req.getQueryMoon();

        for (TabTask task : allTasks) {
            BigDecimal reward = null == task.getReward() ? BigDecimal.ZERO : task.getReward();

            long allBankCount = dataBanks.stream()
                    .filter(data -> task.getId().equals(data.getTaskId()))
                    .count();
            BigDecimal taskBankAllReward = reward.multiply(new BigDecimal(allBankCount));
            historyReward = historyReward.add(taskBankAllReward);

            long thisMoonBankCount = dataBanks.stream()
                    .filter(data -> task.getId().equals(data.getTaskId()))
                    .filter(data -> data.getVerifyDate().getYear() == queryMoon.getYear())
                    .filter(data -> data.getVerifyDate().getMonthValue() == queryMoon.getMonthValue())
                    .count();
            BigDecimal taskBankThisMoonReward = reward.multiply(new BigDecimal(thisMoonBankCount));
            thisMoonReward = thisMoonReward.add(taskBankThisMoonReward);
        }
        CalcuRewardRes res = CalcuRewardRes.builder()
                .historyReward(historyReward)
                .thisMoonReward(thisMoonReward)
                .build();
        return res;
    }

    @Override
    public List<HistoryRewardRes> historyReward(HistoryRewardReq req) {
        String salesmanId = req.getSalesmanId();
        String[] dateStrArray = req.getQueryDateStr().split("-");
        LocalDateTime startTime = LocalDateTime.of(Integer.valueOf(dateStrArray[0]), Integer.valueOf(dateStrArray[1]), 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getMonth().maxLength(), 23, 59, 59);
        // 京东推广 todo


        // 该业务员领取的所有任务
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        salesmanTaskExample.createCriteria()
                .andSalesmanIdEqualTo(salesmanId);
        List<TabSalesmanTask> receiveList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        if (CollectionUtil.isEmpty(receiveList)) {
            return null;
        }
        // 所有业务
        TabTaskExample taskExample = new TabTaskExample();
        taskExample.createCriteria()
                .andIdIn(receiveList.stream().map(TabSalesmanTask::getTaskId).collect(toList()));
        List<TabTask> allTasks = taskMapper.selectByExample(taskExample);
        // 所有通过的开卡订单
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIn(allTasks.stream().map(TabTask::getId).collect(toList()))
                .andSalesmanIdEqualTo(salesmanId)
                .andVerifyDateBetween(startTime, endTime)
        ;


        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);


        List<TabDataBank> taskList = new ArrayList<>();
        for (TabTask task : allTasks) {
            List<TabDataBank> dataBank = dataBanks.stream()
                    .filter(data -> task.getId().equals(data.getTaskId()))
                    .collect(toList());
            taskList.addAll(dataBank);
        }
        List<HistoryRewardRes> resList = new ArrayList<>();
        for (TabDataBank data : taskList) {
            resList.add(HistoryRewardRes.builder()
                    .verifyDate(data.getVerifyDate())
                    .name(data.getBankName())
                    .approveStatus(data.getApproveStatus() == 1 ? "审核通过" : "审核拒绝")
                    .build());
        }
        return resList;
    }


    @Override
    public List<HistoryRewardRes> thisMoonReward(HistoryRewardReq req) {
        String salesmanId = req.getSalesmanId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getMonth().maxLength(), 23, 59, 59);
        // 京东推广 todo

        // 该业务员领取的所有任务
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        salesmanTaskExample.createCriteria()
                .andSalesmanIdEqualTo(salesmanId);
        List<TabSalesmanTask> receiveList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        if (CollectionUtil.isEmpty(receiveList)) {
            return null;
        }
        // 所有业务
        TabTaskExample taskExample = new TabTaskExample();
        taskExample.createCriteria()
                .andIdIn(receiveList.stream().map(TabSalesmanTask::getTaskId).collect(toList()));
        List<TabTask> allTasks = taskMapper.selectByExample(taskExample);
        Map<String, TabTask> taskMap = allTasks.stream()
                .collect(Collectors.toMap(TabTask::getId, Function.identity()));
        // 所有通过的开卡订单
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIn(allTasks.stream().map(TabTask::getId).collect(toList()))
                .andSalesmanIdEqualTo(salesmanId)
                .andVerifyDateBetween(startTime, endTime)
        ;


        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);


        List<TabDataBank> taskList = new ArrayList<>();
        for (TabTask task : allTasks) {
            List<TabDataBank> dataBank = dataBanks.stream()
                    .filter(data -> task.getId().equals(data.getTaskId()))
                    .collect(toList());
            taskList.addAll(dataBank);
        }
        List<HistoryRewardRes> resList = new ArrayList<>();
        for (TabDataBank data : taskList) {
            TabTask task = taskMap.get(data.getTaskId());
            resList.add(HistoryRewardRes.builder()
                    .verifyDate(data.getVerifyDate())
                    .name(data.getBankName())
                    .reward(task.getReward())
                    .approveStatus(data.getApproveStatus() == 1 ? "审核通过" : "审核拒绝")
                    .build());
        }
        return resList;
    }

    @Override
    public List<HistoryRewardRes> bankList(HistoryRewardReq req) {
        String salesmanId = req.getSalesmanId();
        String[] dateStrArray = req.getQueryDateStr().split("-");
        LocalDateTime startTime = LocalDateTime.of(Integer.valueOf(dateStrArray[0]), Integer.valueOf(dateStrArray[1]), 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getMonth().maxLength(), 23, 59, 59);


        // 该业务员领取的所有任务
        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
        salesmanTaskExample.createCriteria()
                .andSalesmanIdEqualTo(salesmanId);
        List<TabSalesmanTask> receiveList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
        if (CollectionUtil.isEmpty(receiveList)) {
            return null;
        }
        // 所有业务
        TabTaskExample taskExample = new TabTaskExample();
        taskExample.createCriteria()
                .andIdIn(receiveList.stream().map(TabSalesmanTask::getTaskId).collect(toList()));
        List<TabTask> allTasks = taskMapper.selectByExample(taskExample);
        Map<String, TabTask> taskMap = allTasks.stream()
                .collect(Collectors.toMap(TabTask::getId, Function.identity()));
        // 所有通过的开卡订单
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIn(allTasks.stream().map(TabTask::getId).collect(toList()))
                .andSalesmanIdEqualTo(salesmanId)
                .andVerifyDateBetween(startTime, endTime)
        ;


        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);


        List<TabDataBank> taskList = new ArrayList<>();
        for (TabTask task : allTasks) {
            List<TabDataBank> dataBank = dataBanks.stream()
                    .filter(data -> task.getId().equals(data.getTaskId()))
                    .collect(toList());
            taskList.addAll(dataBank);
        }
        List<HistoryRewardRes> resList = new ArrayList<>();
        for (TabDataBank data : taskList) {
            TabTask task = taskMap.get(data.getTaskId());
            BigDecimal reward = task.getReward();
            resList.add(HistoryRewardRes.builder()
                    .verifyDate(data.getVerifyDate())
                    .name(data.getBankName())
                    .orderNo(data.getApplyId())
                    .reward(reward)
                    .approveStatus(data.getApproveStatus() == 1 ? "审核通过" : "审核拒绝")
                    .build());
        }
        return resList;
    }

    @Override
    public List<RankingRes> ranking() {
        List<TabSalesman> salesmanList = this.listSalesmanThousand();
        List<RankingRes> result = new ArrayList<>();
        for(TabSalesman salesman : salesmanList) {
            CalcuRewardReq rewardReq = CalcuRewardReq.builder()
                    .salesmanId(salesman.getId())
                    .build();
            CalcuRewardRes calcuRewardRes = this.calcuReward(rewardReq);
            BigDecimal thisMoonReward = calcuRewardRes.getThisMoonReward();
            RankingRes resBean = RankingRes.builder()
                    .name(salesman.getName())
                    .headImg(salesman.getHeadImg())
                    .amount(thisMoonReward)
                    .build();
            result.add(resBean);
        }
        List<RankingRes> res = result.stream()
                .sorted(Comparator.comparing(RankingRes::getAmount).reversed())
                .limit(10)
                .collect(toList());
        return res;
    }
}
