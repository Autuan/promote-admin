package com.autuan.project.promote.redPin.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.redPin.mapper.RedPinMapper;
import com.autuan.project.promote.redPin.domain.RedPin;
import com.autuan.project.promote.redPin.service.IRedPinService;
import com.autuan.common.utils.text.Convert;

/**
 * 红包码信息Service业务层处理
 *
 * @author autuan
 * @date 2020-09-09
 */
@Service
public class RedPinServiceImpl implements IRedPinService {
    @Autowired
    private RedPinMapper redPinMapper;

    /**
     * 查询红包码信息
     *
     * @param id 红包码信息ID
     * @return 红包码信息
     */
    @Override
    public RedPin selectRedPinById(String id) {
        return redPinMapper.selectRedPinById(id);
    }

    /**
     * 查询红包码信息列表
     *
     * @param redPin 红包码信息
     * @return 红包码信息
     */
    @Override
    public List<RedPin> selectRedPinList(RedPin redPin) {
        return redPinMapper.selectRedPinList(redPin);
    }

    /**
     * 新增红包码信息
     *
     * @param redPin 红包码信息
     * @return 结果
     */
    @Override
    public int insertRedPin(RedPin redPin) {
        redPin.setCreateTime(LocalDateTime.now());
        redPin.setCreateBy(ShiroUtils.getLoginName());
        redPin.setId(IdUtil.simpleUUID());
        return redPinMapper.insertRedPin(redPin);
    }

    /**
     * 修改红包码信息
     *
     * @param redPin 红包码信息
     * @return 结果
     */
    @Override
    public int updateRedPin(RedPin redPin) {
        redPin.setUpdateTime(LocalDateTime.now());
        redPin.setUpdateBy(ShiroUtils.getLoginName());
        return redPinMapper.updateRedPin(redPin);
    }

    /**
     * 删除红包码信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRedPinByIds(String ids) {
        return redPinMapper.deleteRedPinByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除红包码信息信息
     *
     * @param id 红包码信息ID
     * @return 结果
     */
    @Override
    public int deleteRedPinById(String id) {
        return redPinMapper.deleteRedPinById(id);
    }
}
