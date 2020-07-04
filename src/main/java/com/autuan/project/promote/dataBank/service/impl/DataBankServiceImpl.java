package com.autuan.project.promote.dataBank.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
                                        import java.time.LocalDateTime;
    import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
            import com.autuan.common.utils.security.ShiroUtils;
                import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.dataBank.mapper.DataBankMapper;
import com.autuan.project.promote.dataBank.domain.DataBank;
import com.autuan.project.promote.dataBank.service.IDataBankService;
import com.autuan.common.utils.text.Convert;

/**
 * 数据导入-开卡订单Service业务层处理
 * 
 * @author autuan
 * @date 2020-07-04
 */
@Service
public class DataBankServiceImpl implements IDataBankService 
{
    @Autowired
    private DataBankMapper dataBankMapper;

    /**
     * 查询数据导入-开卡订单
     * 
     * @param id 数据导入-开卡订单ID
     * @return 数据导入-开卡订单
     */
    @Override
    public DataBank selectDataBankById(String id)
    {
        return dataBankMapper.selectDataBankById(id);
    }

    /**
     * 查询数据导入-开卡订单列表
     * 
     * @param dataBank 数据导入-开卡订单
     * @return 数据导入-开卡订单
     */
    @Override
    public List<DataBank> selectDataBankList(DataBank dataBank)
    {
        return dataBankMapper.selectDataBankList(dataBank);
    }

    /**
     * 新增数据导入-开卡订单
     * 
     * @param dataBank 数据导入-开卡订单
     * @return 结果
     */
    @Override
    public int insertDataBank(DataBank dataBank)
    {
                                                dataBank.setCreateTime(LocalDateTime.now());
                    dataBank.setCreateBy(ShiroUtils.getLoginName());
                            dataBank.setId(IdUtil.simpleUUID());
        return dataBankMapper.insertDataBank(dataBank);
    }

    /**
     * 修改数据导入-开卡订单
     * 
     * @param dataBank 数据导入-开卡订单
     * @return 结果
     */
    @Override
    public int updateDataBank(DataBank dataBank)
    {
                                                dataBank.setUpdateTime(LocalDateTime.now());
                        dataBank.setUpdateBy(ShiroUtils.getLoginName());
                        return dataBankMapper.updateDataBank(dataBank);
    }

    /**
     * 删除数据导入-开卡订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDataBankByIds(String ids)
    {
        return dataBankMapper.deleteDataBankByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除数据导入-开卡订单信息
     * 
     * @param id 数据导入-开卡订单ID
     * @return 结果
     */
    @Override
    public int deleteDataBankById(String id)
    {
        return dataBankMapper.deleteDataBankById(id);
    }
}
