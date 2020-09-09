package com.autuan.project.promote.dataRed.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
                import java.time.LocalDateTime;
    import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
            import com.autuan.common.utils.security.ShiroUtils;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.dataRed.mapper.DataRedMapper;
import com.autuan.project.promote.dataRed.domain.DataRed;
import com.autuan.project.promote.dataRed.service.IDataRedService;
import com.autuan.common.utils.text.Convert;

/**
 * 数据导入-京东红包PINService业务层处理
 * 
 * @author autuan
 * @date 2020-09-09
 */
@Service
public class DataRedServiceImpl implements IDataRedService 
{
    @Autowired
    private DataRedMapper dataRedMapper;

    /**
     * 查询数据导入-京东红包PIN
     * 
     * @param id 数据导入-京东红包PINID
     * @return 数据导入-京东红包PIN
     */
    @Override
    public DataRed selectDataRedById(String id)
    {
        return dataRedMapper.selectDataRedById(id);
    }

    /**
     * 查询数据导入-京东红包PIN列表
     * 
     * @param dataRed 数据导入-京东红包PIN
     * @return 数据导入-京东红包PIN
     */
    @Override
    public List<DataRed> selectDataRedList(DataRed dataRed)
    {
        return dataRedMapper.selectDataRedList(dataRed);
    }

    /**
     * 新增数据导入-京东红包PIN
     * 
     * @param dataRed 数据导入-京东红包PIN
     * @return 结果
     */
    @Override
    public int insertDataRed(DataRed dataRed)
    {
                        dataRed.setCreateTime(LocalDateTime.now());
                    dataRed.setCreateBy(ShiroUtils.getLoginName());
                dataRed.setId(IdUtil.simpleUUID());
        return dataRedMapper.insertDataRed(dataRed);
    }

    /**
     * 修改数据导入-京东红包PIN
     * 
     * @param dataRed 数据导入-京东红包PIN
     * @return 结果
     */
    @Override
    public int updateDataRed(DataRed dataRed)
    {
                        dataRed.setUpdateTime(LocalDateTime.now());
                        dataRed.setUpdateBy(ShiroUtils.getLoginName());
            return dataRedMapper.updateDataRed(dataRed);
    }

    /**
     * 删除数据导入-京东红包PIN对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDataRedByIds(String ids)
    {
        return dataRedMapper.deleteDataRedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除数据导入-京东红包PIN信息
     * 
     * @param id 数据导入-京东红包PINID
     * @return 结果
     */
    @Override
    public int deleteDataRedById(String id)
    {
        return dataRedMapper.deleteDataRedById(id);
    }
}
