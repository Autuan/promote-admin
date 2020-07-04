package com.autuan.project.promote.dataBank.mapper;

import com.autuan.project.promote.dataBank.domain.DataBank;
import java.util.List;

/**
 * 数据导入-开卡订单Mapper接口
 * 
 * @author autuan
 * @date 2020-07-04
 */
public interface DataBankMapper 
{
    /**
     * 查询数据导入-开卡订单
     * 
     * @param id 数据导入-开卡订单ID
     * @return 数据导入-开卡订单
     */
    public DataBank selectDataBankById(String id);

    /**
     * 查询数据导入-开卡订单列表
     * 
     * @param dataBank 数据导入-开卡订单
     * @return 数据导入-开卡订单集合
     */
    public List<DataBank> selectDataBankList(DataBank dataBank);

    /**
     * 新增数据导入-开卡订单
     * 
     * @param dataBank 数据导入-开卡订单
     * @return 结果
     */
    public int insertDataBank(DataBank dataBank);

    /**
     * 修改数据导入-开卡订单
     * 
     * @param dataBank 数据导入-开卡订单
     * @return 结果
     */
    public int updateDataBank(DataBank dataBank);

    /**
     * 删除数据导入-开卡订单
     * 
     * @param id 数据导入-开卡订单ID
     * @return 结果
     */
    public int deleteDataBankById(String id);

    /**
     * 批量删除数据导入-开卡订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataBankByIds(String[] ids);
}
