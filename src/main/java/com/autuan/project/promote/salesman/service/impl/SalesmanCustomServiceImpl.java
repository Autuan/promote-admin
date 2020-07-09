package com.autuan.project.promote.salesman.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.exception.custom.CustomRespondException;
import com.autuan.common.utils.Md5Utils;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.text.Convert;
import com.autuan.project.front.entity.CalcuRewardReq;
import com.autuan.project.front.entity.HistoryRewardReq;
import com.autuan.project.front.entity.HistoryRewardRes;
import com.autuan.project.front.entity.RewardCount;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBankExample;
import com.autuan.project.promote.dataBank.mapper.TabDataBankMapper;
import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.domain.TabDataJdExample;
import com.autuan.project.promote.dataJd.mapper.TabDataJdMapper;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
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
    private TabSalesmanMapper tabSalesmanMapper;
    @Autowired
    private TabSalesmanTaskMapper tabSalesmanTaskMapper;
    @Autowired
    private TabTaskMapper taskMapper;
    @Autowired
    private TabDataJdMapper dataJdMapper;
    @Autowired
    private TabDataBankMapper dataBankMapper;
    @Autowired
    private SalesmanMapper salesmanMapper;
    @Autowired
    private IDataJdCustomService dataJdCustomService;

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
        TabSalesman tabSalesman = tabSalesmanMapper.selectOneByExample(example);
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
        TabSalesman one = tabSalesmanMapper.selectOneByExample(example);
        if (null != one && StrUtil.isNotBlank(one.getId())) {
            throw new CustomRespondException("此手机号已注册,请直接登录");
        }
        salesman.setCreateTime(LocalDateTime.now());
        salesman.setCreateBy("用户注册");
        salesman.setId(IdUtil.simpleUUID());
        salesman.setLevel("普通用户");
        return tabSalesmanMapper.insertSelective(salesman) == 1;
    }

    @Override
    public TabSalesman selectByMobile(String mobile) {
        TabSalesmanExample example = new TabSalesmanExample();
        example.createCriteria()
//                .andMobileLike("%"+mobile+"%")
                .andMobileEqualTo(mobile)
        ;
        TabSalesman tabSalesman = tabSalesmanMapper.selectOneByExample(example);
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
        tabSalesmanMapper.updateByExampleSelective(salesman, example);
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
        tabSalesmanMapper.updateByPrimaryKeySelective(salesman);
    }

    @Override
    public List<TabSalesman> listSalesmanThousand() {
        TabSalesmanExample example = new TabSalesmanExample();
        example.limit(1000);
        return tabSalesmanMapper.selectByExample(example);
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
//        TabSalesmanTaskExample salesmanTaskExample = new TabSalesmanTaskExample();
//        salesmanTaskExample.createCriteria()
//                .andSalesmanIdEqualTo(salesmanId);
//        List<TabSalesmanTask> receiveList = tabSalesmanTaskMapper.selectByExample(salesmanTaskExample);
//        if (CollectionUtil.isEmpty(receiveList)) {
//            return CalcuRewardRes.zero();
//        }
        // 所有业务
//        TabTaskExample taskExample = new TabTaskExample();
//        taskExample.createCriteria()
//                .andIdIn(receiveList.stream().map(TabSalesmanTask::getTaskId).collect(toList()));
//        List<TabTask> allTasks = taskMapper.selectByExample(taskExample);


        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), 1, 0, 0, 0);
//        LocalDateTime endTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getMonth().maxLength(), 23, 59, 59);

        // 开卡订单
        // todo magic val
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIsNotNull()
                .andSalesmanIdEqualTo(salesmanId)
                // 1:是
                .andCustomFlagEqualTo(1)
                // 1:通过
                .andApproveStatusEqualTo(1)
//        .andApplyDateBetween(startTime,endTime)
        ;
        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);

        // 京东推广
        TabDataJdExample dataJdExample = new TabDataJdExample();
        dataJdExample.createCriteria()
                .andTaskIdIsNotNull()
                .andSalesmanIdEqualTo(salesmanId)
//        .andOpenJdCreditTimeBetween(startTime,endTime)
                // 1:是
//                .andCustomFlagEqualTo(1)
                // 1:通过
//                .andApproveStatusEqualTo(1)
        ;
        List<TabDataJd> dataJds = dataJdMapper.selectByExample(dataJdExample);

        Set<String> taskIdSet = dataJds.stream()
                .map(TabDataJd::getTaskId)
                .collect(Collectors.toSet());
        Map<String, BigDecimal> rewardOption = dataJdCustomService.getRewardOption(taskIdSet);
        // 本月预估推广费
        BigDecimal thisMoonReward = BigDecimal.ZERO;
        // 累计推广费
        BigDecimal historyReward = BigDecimal.ZERO;


        BigDecimal thisMoonBankCount = dataBanks.stream()
                .filter(data -> data.getVerifyDate().getYear() == now.getYear())
                .filter(data -> data.getVerifyDate().getMonthValue() == now.getMonthValue())
                .map(TabDataBank::getReward)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal historyRewardBankCount = dataBanks.stream()
                .map(TabDataBank::getReward)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal thisMoonJdCount = BigDecimal.ZERO;
        BigDecimal historyRewardJdCount = BigDecimal.ZERO;

        for(TabDataJd data : dataJds){
            String taskId = data.getTaskId();
            Integer type = data.getOpenJdCreditType();
            BigDecimal reward = BigDecimal.ZERO;
            switch (type){
                case 0 : reward =rewardOption.get("rewardCommon"+taskId);break;
                case 1 : reward =rewardOption.get("rewardGold"+taskId);break;
                case 2 : reward =rewardOption.get("rewardNewbie"+taskId);break;
                default:break;
            }
            historyRewardJdCount = historyRewardJdCount.add(reward);
            boolean isThisMoon = data.getOpenJdCreditTime().getYear() == now.getYear()
                    && data.getOpenJdCreditTime().getMonthValue() == now.getMonthValue();
            if(isThisMoon) {
                thisMoonJdCount = thisMoonJdCount.add(reward);
            }
        }
        historyReward = historyRewardJdCount.add(historyRewardBankCount);
        thisMoonReward = thisMoonBankCount.add(thisMoonJdCount);
        CalcuRewardRes res = CalcuRewardRes.builder()
                .historyReward(historyReward)
                .thisMoonReward(thisMoonReward)
                .build();
        return res;
    }

    @Override
    public List<RewardCount> historyReward(HistoryRewardReq req) {
        // 查询所有月
//        TabSalesmanExample salesmanExample = new TabSalesmanExample();
//        salesmanExample.createCriteria()
//                .andIdEqualTo(req.getSalesmanId());
//        TabSalesman salesman = tabSalesmanMapper.selectOneByExample(salesmanExample);
        // 可能导入更早的数据,写死开始月
        LocalDateTime queryTime = LocalDateTime.of(2020,6,1,0,0);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getMonth().maxLength(), 23, 59, 59);
        List<RewardCount> result = new ArrayList<>();
        while (queryTime.isBefore(endTime)) {
            LocalDateTime queryStartTime = LocalDateTime.of(queryTime.getYear(), queryTime.getMonthValue(), 1, 0, 0, 0);
            LocalDateTime queryEndTime = LocalDateTime.of(queryStartTime.getYear(), queryStartTime.getMonthValue(), queryStartTime.getMonth().maxLength(), 23,59,59);

            req.setQueryDateStart(queryStartTime);
            req.setQueryDateEnd(queryEndTime);

            List<HistoryRewardRes> queryResult = queryMoonData(req);

            RewardCount bean = RewardCount.builder()
                    .month(queryStartTime)
                    .count(queryResult.stream().map(HistoryRewardRes::getReward).reduce(BigDecimal.ZERO, BigDecimal::add))
                    .build();
            result.add(bean);

            queryTime = queryTime.plusMonths(1);
        }

        return  CollectionUtil.reverse(result);
    }


    @Override
    public List<HistoryRewardRes> thisMoonReward(HistoryRewardReq req) {
        String[] dateStrArray = req.getQueryDateStr().split("-");
        LocalDateTime startTime = LocalDateTime.of(Integer.valueOf(dateStrArray[0]), Integer.valueOf(dateStrArray[1]), 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getMonth().maxLength(), 23, 59, 59);

        req.setQueryDateStart(startTime);
        req.setQueryDateEnd(endTime);
        return queryMoonData(req);

    }

    @Override
    public List<HistoryRewardRes> bankList(HistoryRewardReq req) {
        String salesmanId = req.getSalesmanId();
        String[] dateStrArray = req.getQueryDateStr().split("-");
        LocalDateTime startTime = LocalDateTime.of(Integer.valueOf(dateStrArray[0]), Integer.valueOf(dateStrArray[1]), 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonthValue(), startTime.getMonth().maxLength(), 23, 59, 59);


        // 开卡订单
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIsNotNull()
//                .andTaskIdIn(allTasks.stream().map(TabTask::getId).collect(toList()))
                .andChannelCodeIsNotNull()
                .andSalesmanIdEqualTo(salesmanId)
                .andVerifyDateBetween(startTime, endTime)
        ;

        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);


//        List<TabDataBank> taskList = new ArrayList<>();
//        for (TabTask task : allTasks) {
//            List<TabDataBank> dataBank = dataBanks.stream()
//                    .filter(data -> task.getId().equals(data.getTaskId()))
//                    .collect(toList());
//            taskList.addAll(dataBank);
//        }
        List<HistoryRewardRes> resList = new ArrayList<>();
        for (TabDataBank data : dataBanks) {
//            TabTask task = taskMap.get(data.getTaskId());
//            BigDecimal reward = task.getReward();
            resList.add(HistoryRewardRes.builder()
                    .verifyDate(data.getVerifyDate())
                    .name(data.getBankName())
                    .orderNo(data.getApplyId())
                    .reward(data.getReward())
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

    private List<HistoryRewardRes> queryMoonData(HistoryRewardReq req){
        List<HistoryRewardRes> resList = new ArrayList<>();

        String salesmanId = req.getSalesmanId();
        LocalDateTime startTime = req.getQueryDateStart();
        LocalDateTime endTime =req.getQueryDateEnd();


        // 所有通过的开卡订单
        TabDataBankExample dataBankExample = new TabDataBankExample();
        dataBankExample.createCriteria()
                .andTaskIdIsNotNull()
                .andSalesmanIdEqualTo(salesmanId)
                .andVerifyDateBetween(startTime, endTime);
        List<TabDataBank> dataBanks = dataBankMapper.selectByExample(dataBankExample);
        // 京东推广
        TabDataJdExample dataJdExample = new TabDataJdExample();
        dataJdExample.createCriteria()
                .andTaskIdIsNotNull()
                .andSalesmanIdEqualTo(salesmanId)
                .andOpenJdCreditTimeBetween(startTime,endTime);
        List<TabDataJd> dataJds = dataJdMapper.selectByExample(dataJdExample);
        // 任务ID集合
        List<String> taskIds = dataBanks.stream().map(TabDataBank::getTaskId).collect(toList());
        List<String> collect = dataJds.stream().map(TabDataJd::getTaskId).collect(toList());
        taskIds.addAll(collect);
        if(CollectionUtil.isEmpty(taskIds)) {
            return resList;
        }
        TabTaskExample taskExample = new TabTaskExample();
        taskExample.createCriteria()
                .andIdIn(taskIds);
        List<TabTask> allTasks = taskMapper.selectByExample(taskExample);
        Map<String, TabTask> taskMap = allTasks.stream()
                .collect(Collectors.toMap(TabTask::getId, Function.identity(),(existing, replacement) -> existing));

        // 响应数据
        for (TabDataBank data : dataBanks) {
            TabTask task = taskMap.get(data.getTaskId());
            // todo magic num
            boolean isPass = data.getApproveStatus() == 1 && data.getCustomFlag() == 1;
            resList.add(HistoryRewardRes.builder()
                    .verifyDate(data.getVerifyDate())
                    .name(data.getBankName())
                    .reward(data.getReward())
                    .approveStatus(isPass ? "通过" : "拒绝")
                    .build());
        }
        Set<String> taskIdSet = dataJds.stream()
                .map(TabDataJd::getTaskId)
                .collect(Collectors.toSet());
        Map<String, BigDecimal> rewardOption = dataJdCustomService.getRewardOption(taskIdSet);
        for (TabDataJd data : dataJds) {
            String taskId = data.getTaskId();
                BigDecimal rewardNewbie = rewardOption.get("rewardNewbie"+taskId);
                BigDecimal rewardCommon = rewardOption.get("rewardCommon"+taskId);
                BigDecimal rewardGold = rewardOption.get("rewardGold"+taskId);

                Integer type = data.getOpenJdCreditType();
                BigDecimal reward = rewardCommon;
                switch (type){
                    case 0 : reward =rewardCommon;break;
                    case 1 : reward =rewardGold;break;
                    case 2 : reward =rewardNewbie;break;
                    default:break;
                }
                HistoryRewardRes bean = HistoryRewardRes.builder()
                        .verifyDate(data.getOpenJdCreditTime())
                        .name(data.getOrderName())
                        .reward(reward)
                        .approveStatus("通过")
                        .build();
            resList.add(bean);
        }
        resList = resList.stream()
                .sorted(Comparator.comparing(HistoryRewardRes::getVerifyDate))
                .collect(toList());
        return resList;
    }


    @Override
    public String importData(List<Salesman> list, boolean isUpdateSupport) {
        if (CollectionUtil.isEmpty(list)) {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String operName = ShiroUtils.getLoginName();
        LocalDateTime now = LocalDateTime.now();

//        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Salesman data : list) {
            try {
                // 验证是否存在这个用户
                Salesman oldBean = null;
                if (null == oldBean) {
                    // 默认值
                    data.setId(IdUtil.simpleUUID());
                    data.setCreateBy(operName);
                    data.setCreateTime(now);
                    data.setApplyTime(now);
                    if(StrUtil.isBlank(data.getPassword())) {
                        data.setPassword(Md5Utils.hash("123456"));
                    }
                    if(StrUtil.isBlank(data.getHeadImg())) {
                        data.setHeadImg("http://promote.yupai.net/admin/profile/upload/def/head_img_def.jpg");
                    }
                    if (StrUtil.isBlank(data.getLevel())) {
                        data.setLevel("普通会员");
                    }
                    if(StrUtil.isBlank(data.getBrokerageBankNo())) {
                        data.setBrokerageBankNo("0000");
                    }
                    if(StrUtil.isBlank(data.getBrokerageBankName())) {
                        data.setBrokerageBankName("Bank Name");
                    }
                    if(StrUtil.isBlank(data.getBrokerageBankAddress())) {
                        data.setBrokerageBankAddress("Bank Address");
                    }
                    if(null == data.getGender()) {
                        data.setGender(0);
                    }
                    salesmanMapper.insertSalesman(data);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + data.getName() + " 导入成功");
                } else if (isUpdateSupport) {
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + data.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + data.getName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + data.getName() + " 导入失败：";
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
