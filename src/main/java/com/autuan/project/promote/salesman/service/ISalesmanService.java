package com.autuan.project.promote.salesman.service;

import com.autuan.project.promote.salesman.domain.Salesman;
import java.util.List;

/**
 * 业务员Service接口
 * 
 * @author autuan
 * @date 2020-06-18
 */
public interface ISalesmanService 
{
    /**
     * 查询业务员
     * 
     * @param id 业务员ID
     * @return 业务员
     */
    public Salesman selectSalesmanById(String id);

    /**
     * 查询业务员列表
     * 
     * @param salesman 业务员
     * @return 业务员集合
     */
    public List<Salesman> selectSalesmanList(Salesman salesman);

    /**
     * 新增业务员
     * 
     * @param salesman 业务员
     * @return 结果
     */
    public int insertSalesman(Salesman salesman);

    /**
     * 修改业务员
     * 
     * @param salesman 业务员
     * @return 结果
     */
    public int updateSalesman(Salesman salesman);

    /**
     * 批量删除业务员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesmanByIds(String ids);

    /**
     * 删除业务员信息
     * 
     * @param id 业务员ID
     * @return 结果
     */
    public int deleteSalesmanById(String id);
}
