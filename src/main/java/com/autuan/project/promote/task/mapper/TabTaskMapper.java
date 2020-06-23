package com.autuan.project.promote.task.mapper;

import com.autuan.project.promote.task.domain.TabTask;
import com.autuan.project.promote.task.domain.TabTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabTaskMapper {
    long countByExample(TabTaskExample example);

    int deleteByExample(TabTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabTask record);

    int insertSelective(@Param("record") TabTask record, @Param("selective") TabTask.Column ... selective);

    TabTask selectOneByExample(TabTaskExample example);

    List<TabTask> selectByExample(TabTaskExample example);

    TabTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabTask record, @Param("example") TabTaskExample example, @Param("selective") TabTask.Column ... selective);

    int updateByExample(@Param("record") TabTask record, @Param("example") TabTaskExample example);

    int updateByPrimaryKeySelective(@Param("record") TabTask record, @Param("selective") TabTask.Column ... selective);

    int updateByPrimaryKey(TabTask record);

    int batchInsert(@Param("list") List<TabTask> list);

    int batchInsertSelective(@Param("list") List<TabTask> list, @Param("selective") TabTask.Column ... selective);
}