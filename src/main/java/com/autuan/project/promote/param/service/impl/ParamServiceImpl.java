package com.autuan.project.promote.param.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
                import java.time.LocalDateTime;
    import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
            import com.autuan.common.utils.security.ShiroUtils;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.param.mapper.ParamMapper;
import com.autuan.project.promote.param.domain.Param;
import com.autuan.project.promote.param.service.IParamService;
import com.autuan.common.utils.text.Convert;

/**
 * 任务链接参数Service业务层处理
 * 
 * @author autuan
 * @date 2020-06-23
 */
@Service
public class ParamServiceImpl implements IParamService 
{
    @Autowired
    private ParamMapper paramMapper;

    /**
     * 查询任务链接参数
     * 
     * @param id 任务链接参数ID
     * @return 任务链接参数
     */
    @Override
    public Param selectParamById(String id)
    {
        return paramMapper.selectParamById(id);
    }

    /**
     * 查询任务链接参数列表
     * 
     * @param param 任务链接参数
     * @return 任务链接参数
     */
    @Override
    public List<Param> selectParamList(Param param)
    {
        return paramMapper.selectParamList(param);
    }

    /**
     * 新增任务链接参数
     * 
     * @param param 任务链接参数
     * @return 结果
     */
    @Override
    public int insertParam(Param param)
    {
                        param.setCreateTime(LocalDateTime.now());
                    param.setCreateBy(ShiroUtils.getLoginName());
                    param.setId(IdUtil.simpleUUID());
        return paramMapper.insertParam(param);
    }

    /**
     * 修改任务链接参数
     * 
     * @param param 任务链接参数
     * @return 结果
     */
    @Override
    public int updateParam(Param param)
    {
                        param.setUpdateTime(LocalDateTime.now());
                        param.setUpdateBy(ShiroUtils.getLoginName());
                return paramMapper.updateParam(param);
    }

    /**
     * 删除任务链接参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteParamByIds(String ids)
    {
        return paramMapper.deleteParamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务链接参数信息
     * 
     * @param id 任务链接参数ID
     * @return 结果
     */
    @Override
    public int deleteParamById(String id)
    {
        return paramMapper.deleteParamById(id);
    }
}
