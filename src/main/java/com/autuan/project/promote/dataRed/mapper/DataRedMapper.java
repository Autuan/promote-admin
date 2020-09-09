package com.autuan.project.promote.dataRed.mapper;

import com.autuan.project.promote.dataRed.domain.DataRed;
import java.util.List;

/**
 * 数据导入-京东红包PINMapper接口
 * 
 * @author autuan
 * @date 2020-09-09
 */
public interface DataRedMapper 
{
    /**
     * 查询数据导入-京东红包PIN
     * 
     * @param id 数据导入-京东红包PINID
     * @return 数据导入-京东红包PIN
     */
    public DataRed selectDataRedById(String id);

    /**
     * 查询数据导入-京东红包PIN列表
     * 
     * @param dataRed 数据导入-京东红包PIN
     * @return 数据导入-京东红包PIN集合
     */
    public List<DataRed> selectDataRedList(DataRed dataRed);

    /**
     * 新增数据导入-京东红包PIN
     * 
     * @param dataRed 数据导入-京东红包PIN
     * @return 结果
     */
    public int insertDataRed(DataRed dataRed);

    /**
     * 修改数据导入-京东红包PIN
     * 
     * @param dataRed 数据导入-京东红包PIN
     * @return 结果
     */
    public int updateDataRed(DataRed dataRed);

    /**
     * 删除数据导入-京东红包PIN
     * 
     * @param id 数据导入-京东红包PINID
     * @return 结果
     */
    public int deleteDataRedById(String id);

    /**
     * 批量删除数据导入-京东红包PIN
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataRedByIds(String[] ids);
}
