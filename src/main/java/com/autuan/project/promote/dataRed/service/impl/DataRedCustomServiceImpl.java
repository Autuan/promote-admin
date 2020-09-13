package com.autuan.project.promote.dataRed.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.dataRed.domain.TabDataRed;
import com.autuan.project.promote.dataRed.mapper.TabDataRedMapper;
import com.autuan.project.promote.dataRed.service.IDataRedCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: DataRedCustomServiceImpl
 * @author: sen.zhou
 * @description:
 * @date: 2020/9/13 21:22
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Service
@Slf4j
public class DataRedCustomServiceImpl implements IDataRedCustomService {
    @Autowired
    private TabDataRedMapper tabDataRedMapper;
    @Override
    public String importExcel(List<TabDataRed> list) {
        StringBuilder strBuilder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String loginName = ShiroUtils.getLoginName();

        // 插入
        List<TabDataRed> insertList = new ArrayList<>();
        int line = 0;
        int errorNum = 0;
        for (TabDataRed insertBean : list) {
            line++;

            insertBean.setCreateTime(now);
            insertBean.setCreateBy(loginName);
            insertBean.setId(IdUtil.simpleUUID());

            // 只有符合条件的置入奖励值
//            Integer customFlag = dataBank.getCustomFlag();
//            Integer approveStatus = dataBank.getApproveStatus();
//            if (customFlag.equals(YES) && approveStatus.equals(YES)) {
//                dataBank.setReward(task.getReward());
//            } else {
//                dataBank.setReward(BigDecimal.ZERO);
//            }
            insertList.add(insertBean);
        }
        if (CollectionUtil.isNotEmpty(insertList)) {
            tabDataRedMapper.batchInsert(insertList);
        }

        strBuilder.insert(0, "共 " + list.size() + " 条数据,成功：" + (list.size() - errorNum) + " 失败: " + errorNum);
        return strBuilder.toString();
    }
}