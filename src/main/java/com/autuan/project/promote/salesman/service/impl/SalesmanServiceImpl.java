package com.autuan.project.promote.salesman.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

import cn.hutool.crypto.SecureUtil;
import com.autuan.common.utils.Md5Utils;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.salesman.mapper.SalesmanMapper;
import com.autuan.project.promote.salesman.domain.Salesman;
import com.autuan.project.promote.salesman.service.ISalesmanService;
import com.autuan.common.utils.text.Convert;

/**
 * 业务员Service业务层处理
 *
 * @author autuan
 * @date 2020-06-18
 */
@Service
public class SalesmanServiceImpl implements ISalesmanService {
    @Autowired
    private SalesmanMapper salesmanMapper;

    /**
     * 查询业务员
     *
     * @param id 业务员ID
     * @return 业务员
     */
    @Override
    public Salesman selectSalesmanById(String id) {
        return salesmanMapper.selectSalesmanById(id);
    }

    /**
     * 查询业务员列表
     *
     * @param salesman 业务员
     * @return 业务员
     */
    @Override
    public List<Salesman> selectSalesmanList(Salesman salesman) {
        return salesmanMapper.selectSalesmanList(salesman);
    }

    /**
     * 新增业务员
     *
     * @param salesman 业务员
     * @return 结果
     */
    @Override
    public int insertSalesman(Salesman salesman) {
        salesman.setCreateTime(LocalDateTime.now());
        salesman.setCreateBy(ShiroUtils.getLoginName());
        salesman.setId(IdUtil.simpleUUID());
        // 申请日期
        salesman.setApplyTime(LocalDateTime.now());
        // 默认密码 & 密码加密
        String pwd = Md5Utils.hash("123456");
//        byte[] key = SecureUtil.generateKey(salesman.getMobile()).getEncoded();
//        String pwd = SecureUtil.aes(key).encryptHex("123456");
        salesman.setPassword(pwd);
        return salesmanMapper.insertSalesman(salesman);
    }

    /**
     * 修改业务员
     *
     * @param salesman 业务员
     * @return 结果
     */
    @Override
    public int updateSalesman(Salesman salesman) {
        salesman.setUpdateTime(LocalDateTime.now());
        salesman.setUpdateBy(ShiroUtils.getLoginName());
        return salesmanMapper.updateSalesman(salesman);
    }

    /**
     * 删除业务员对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSalesmanByIds(String ids) {
        return salesmanMapper.deleteSalesmanByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除业务员信息
     *
     * @param id 业务员ID
     * @return 结果
     */
    @Override
    public int deleteSalesmanById(String id) {
        return salesmanMapper.deleteSalesmanById(id);
    }
}
