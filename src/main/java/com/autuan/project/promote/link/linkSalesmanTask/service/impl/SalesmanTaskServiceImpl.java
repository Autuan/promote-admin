package com.autuan.project.promote.link.linkSalesmanTask.service.impl;

import java.util.List;
import cn.hutool.core.util.IdUtil;
                                import java.time.LocalDateTime;
    import java.time.LocalDateTime;
            import com.autuan.common.utils.security.ShiroUtils;
            import com.autuan.common.utils.security.ShiroUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.autuan.project.promote.link.linkSalesmanTask.mapper.SalesmanTaskMapper;
import com.autuan.project.promote.link.linkSalesmanTask.domain.SalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.service.ISalesmanTaskService;
import com.autuan.common.utils.text.Convert;

/**
 * 业务员-任务中间Service业务层处理
 * 
 * @author autuan
 * @date 2020-07-02
 */
@Service
public class SalesmanTaskServiceImpl implements ISalesmanTaskService 
{
    @Autowired
    private SalesmanTaskMapper salesmanTaskMapper;

    /**
     * 查询业务员-任务中间
     * 
     * @param id 业务员-任务中间ID
     * @return 业务员-任务中间
     */
    @Override
    public SalesmanTask selectSalesmanTaskById(String id)
    {
        return salesmanTaskMapper.selectSalesmanTaskById(id);
    }

    /**
     * 查询业务员-任务中间列表
     * 
     * @param salesmanTask 业务员-任务中间
     * @return 业务员-任务中间
     */
    @Override
    public List<SalesmanTask> selectSalesmanTaskList(SalesmanTask salesmanTask)
    {
        return salesmanTaskMapper.selectSalesmanTaskList(salesmanTask);
    }

    /**
     * 新增业务员-任务中间
     * 
     * @param salesmanTask 业务员-任务中间
     * @return 结果
     */
    @Override
    public int insertSalesmanTask(SalesmanTask salesmanTask)
    {
                                        salesmanTask.setCreateTime(LocalDateTime.now());
                    salesmanTask.setCreateBy(ShiroUtils.getLoginName());
                        salesmanTask.setId(IdUtil.simpleUUID());
        return salesmanTaskMapper.insertSalesmanTask(salesmanTask);
    }

    /**
     * 修改业务员-任务中间
     * 
     * @param salesmanTask 业务员-任务中间
     * @return 结果
     */
    @Override
    public int updateSalesmanTask(SalesmanTask salesmanTask)
    {
                                        salesmanTask.setUpdateTime(LocalDateTime.now());
                        salesmanTask.setUpdateBy(ShiroUtils.getLoginName());
                    return salesmanTaskMapper.updateSalesmanTask(salesmanTask);
    }

    /**
     * 删除业务员-任务中间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSalesmanTaskByIds(String ids)
    {
        return salesmanTaskMapper.deleteSalesmanTaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除业务员-任务中间信息
     * 
     * @param id 业务员-任务中间ID
     * @return 结果
     */
    @Override
    public int deleteSalesmanTaskById(String id)
    {
        return salesmanTaskMapper.deleteSalesmanTaskById(id);
    }
}
