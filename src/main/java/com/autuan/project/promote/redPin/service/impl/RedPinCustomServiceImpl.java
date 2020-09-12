package com.autuan.project.promote.redPin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.text.Convert;
import com.autuan.project.promote.dataRed.domain.TabDataRedExample;
import com.autuan.project.promote.dataRed.mapper.TabDataRedMapper;
import com.autuan.project.promote.redPin.domain.CheckDO;
import com.autuan.project.promote.redPin.domain.TabRedPin;
import com.autuan.project.promote.redPin.domain.TabRedPinExample;
import com.autuan.project.promote.redPin.mapper.RedPinCustomMapper;
import com.autuan.project.promote.redPin.mapper.TabRedPinMapper;
import com.autuan.project.promote.redPin.service.IRedPinCustomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @className: RedPinCustomServiceImpl
 * @author: sen.zhou
 * @description:
 * @date: 2020/9/12 20:13
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
@Service
@Slf4j
public class RedPinCustomServiceImpl implements IRedPinCustomService{
    @Autowired
    private TabRedPinMapper redPinMapper;
    @Autowired
    private TabDataRedMapper dataRedMapper;
    @Autowired
    private RedPinCustomMapper redPinCustomMapper;
    @Override
    public void add(TabRedPin redPin) {
        // redPin 重复判断
        TabRedPinExample example = new TabRedPinExample();
        example.createCriteria()
                .andRedPinEqualTo(redPin.getRedPin());
        TabRedPin bean = redPinMapper.selectOneByExample(example);
        if (null != bean) {
            throw new BusinessException("该京东红包码已存在");
        }
        redPin.setId(IdUtil.simpleUUID());
        redPin.setCreateBy(ShiroUtils.getLoginName());
        redPin.setCreateTime(LocalDateTime.now());

        redPinMapper.insertSelective(redPin);
    }

    @Override
    public int deleteRedPinByIds(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        // 有数据就不能删除
        List<CheckDO> checkList = redPinCustomMapper.redPinDelCheck(idList);
        if (CollectionUtil.isNotEmpty(checkList)) {
            throw new BusinessException("选择KEY拥有数据,不能删除");
        }

        TabRedPinExample example = new TabRedPinExample();
        example.createCriteria()
                .andIdIn(idList);
        return redPinMapper.deleteByExample(example);
    }
}