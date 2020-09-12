package com.autuan.project.promote.redPin.service.impl;

import cn.hutool.core.util.IdUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.redPin.domain.TabRedPin;
import com.autuan.project.promote.redPin.domain.TabRedPinExample;
import com.autuan.project.promote.redPin.mapper.TabRedPinMapper;
import com.autuan.project.promote.redPin.service.IRedPinCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}