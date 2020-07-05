package com.autuan.project.promote.dataJd.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
                                                                                                                                                        import java.time.LocalDateTime;
    import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
            import com.autuan.common.utils.security.ShiroUtils;
                    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.dataJd.mapper.DataJdMapper;
import com.autuan.project.promote.dataJd.domain.DataJd;
import com.autuan.project.promote.dataJd.service.IDataJdService;
import com.autuan.common.utils.text.Convert;

/**
 * 京东联合拉新数据Service业务层处理
 * 
 * @author autuan
 * @date 2020-07-05
 */
@Service
public class DataJdServiceImpl implements IDataJdService 
{
    @Autowired
    private DataJdMapper dataJdMapper;

    /**
     * 查询京东联合拉新数据
     * 
     * @param id 京东联合拉新数据ID
     * @return 京东联合拉新数据
     */
    @Override
    public DataJd selectDataJdById(String id)
    {
        return dataJdMapper.selectDataJdById(id);
    }

    /**
     * 查询京东联合拉新数据列表
     * 
     * @param dataJd 京东联合拉新数据
     * @return 京东联合拉新数据
     */
    @Override
    public List<DataJd> selectDataJdList(DataJd dataJd)
    {
        return dataJdMapper.selectDataJdList(dataJd);
    }

    /**
     * 新增京东联合拉新数据
     * 
     * @param dataJd 京东联合拉新数据
     * @return 结果
     */
    @Override
    public int insertDataJd(DataJd dataJd)
    {
                                                                                                                                                                dataJd.setCreateTime(LocalDateTime.now());
                    dataJd.setCreateBy(ShiroUtils.getLoginName());
                                dataJd.setId(IdUtil.simpleUUID());
        return dataJdMapper.insertDataJd(dataJd);
    }

    /**
     * 修改京东联合拉新数据
     * 
     * @param dataJd 京东联合拉新数据
     * @return 结果
     */
    @Override
    public int updateDataJd(DataJd dataJd)
    {
                                                                                                                                                                dataJd.setUpdateTime(LocalDateTime.now());
                        dataJd.setUpdateBy(ShiroUtils.getLoginName());
                            return dataJdMapper.updateDataJd(dataJd);
    }

    /**
     * 删除京东联合拉新数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDataJdByIds(String ids)
    {
        return dataJdMapper.deleteDataJdByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除京东联合拉新数据信息
     * 
     * @param id 京东联合拉新数据ID
     * @return 结果
     */
    @Override
    public int deleteDataJdById(String id)
    {
        return dataJdMapper.deleteDataJdById(id);
    }
}
