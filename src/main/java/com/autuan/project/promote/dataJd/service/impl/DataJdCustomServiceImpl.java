package com.autuan.project.promote.dataJd.service.impl;

import cn.hutool.core.util.IdUtil;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.mapper.TabDataJdMapper;
import com.autuan.project.promote.dataJd.service.IDataJdCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        for (TabDataJd data : list) {
            data.setCreateTime(now);
            data.setCreateBy(loginName);
            data.setId(IdUtil.simpleUUID());
        }
        dataJdMapper.batchInsert(list);
    }
}
