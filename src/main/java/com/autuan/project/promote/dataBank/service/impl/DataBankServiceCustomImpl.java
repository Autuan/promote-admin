package com.autuan.project.promote.dataBank.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.autuan.common.exception.BusinessException;
import com.autuan.common.utils.security.ShiroUtils;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.mapper.TabDataBankMapper;
import com.autuan.project.promote.dataBank.service.IDataBankCustomService;
import com.autuan.project.promote.dataBank.service.IDataBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public void importExcel(List<TabDataBank> list) {
        dataBankMapper.batchInsert(list);

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
        String operName = ShiroUtils.getLoginName();
//        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (DataBank dataBank : list) {
            try {
                // 验证是否存在这个用户
//                User u = userMapper.selectUserByLoginName(user.getLoginName());
                DataBank data = null;
                if (null == data) {
//                    user.setPassword(password);
//                    user.setCreateBy(operName);
                    dataBankService.insertDataBank(dataBank);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + dataBank.getCName() + " 导入成功");
                } else if (isUpdateSupport) {
//                    user.setUpdateBy(operName);
//                    this.updateUser(user);
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