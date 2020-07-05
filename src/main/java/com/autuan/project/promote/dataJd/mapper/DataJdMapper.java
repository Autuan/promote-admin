package com.autuan.project.promote.dataJd.mapper;

import com.autuan.project.promote.dataJd.domain.DataJd;
import java.util.List;

/**
 * 京东联合拉新数据Mapper接口
 * 
 * @author autuan
 * @date 2020-07-05
 */
public interface DataJdMapper 
{
    /**
     * 查询京东联合拉新数据
     * 
     * @param id 京东联合拉新数据ID
     * @return 京东联合拉新数据
     */
    public DataJd selectDataJdById(String id);

    /**
     * 查询京东联合拉新数据列表
     * 
     * @param dataJd 京东联合拉新数据
     * @return 京东联合拉新数据集合
     */
    public List<DataJd> selectDataJdList(DataJd dataJd);

    /**
     * 新增京东联合拉新数据
     * 
     * @param dataJd 京东联合拉新数据
     * @return 结果
     */
    public int insertDataJd(DataJd dataJd);

    /**
     * 修改京东联合拉新数据
     * 
     * @param dataJd 京东联合拉新数据
     * @return 结果
     */
    public int updateDataJd(DataJd dataJd);

    /**
     * 删除京东联合拉新数据
     * 
     * @param id 京东联合拉新数据ID
     * @return 结果
     */
    public int deleteDataJdById(String id);

    /**
     * 批量删除京东联合拉新数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataJdByIds(String[] ids);
}
