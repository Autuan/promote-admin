package com.autuan.project.promote.task.service.impl;

import cn.hutool.core.util.IdUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.param.domain.TabParamExample;
import com.autuan.project.promote.param.mapper.TabParamMapper;
import com.autuan.project.promote.task.domain.SetTaskParamAO;
import com.autuan.project.promote.task.service.ITaskCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/6/23 14:41
 * @company : 上海奥若拉信息科技集团有限公司
 */
@Service
@Slf4j
public class TabCustomServiceImpl implements ITaskCustomService {
    @Autowired
    private TabParamMapper paramMapper;
    /**
     * 设置参数
     *
     * @param ao
     * @throws
     * @author : Autuan.Yu
     * @return: void
     * @since : 2020/6/23 14:42
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setTaskParam(SetTaskParamAO ao) {
        List<TabParam> list = ao.getList();
        String taskId = list.get(0).getTaskId();
        TabParamExample example = new TabParamExample();
        example.createCriteria()
                .andTaskIdEqualTo(taskId);
        // 删除后重新添加
        paramMapper.deleteByExample(example);
        for (TabParam param : list) {
            param.setCreateTime(LocalDateTime.now());
            param.setCreateBy(ShiroUtils.getLoginName());
            param.setId(IdUtil.simpleUUID());
        }
        paramMapper.batchInsert(list);
    }

    /**
     * 获取设置的配置
     *
     * @param taskId@throws
     * @author : Autuan.Yu
     * @return: java.util.List<com.autuan.project.promote.param.domain.TabParam>
     * @since : 2020/6/23 14:49
     */
    @Override
    public List<TabParam> getParamList(String taskId) {
        TabParamExample example = new TabParamExample();
        example.createCriteria()
                .andTaskIdEqualTo(taskId);
        return paramMapper.selectByExample(example);
    }
}
