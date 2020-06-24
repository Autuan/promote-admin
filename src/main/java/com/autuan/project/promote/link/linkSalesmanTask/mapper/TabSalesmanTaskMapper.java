package com.autuan.project.promote.link.linkSalesmanTask.mapper;

import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTask;
import com.autuan.project.promote.link.linkSalesmanTask.domain.TabSalesmanTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabSalesmanTaskMapper {
    long countByExample(TabSalesmanTaskExample example);

    int deleteByExample(TabSalesmanTaskExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabSalesmanTask record);

    int insertSelective(@Param("record") TabSalesmanTask record, @Param("selective") TabSalesmanTask.Column ... selective);

    TabSalesmanTask selectOneByExample(TabSalesmanTaskExample example);

    List<TabSalesmanTask> selectByExample(TabSalesmanTaskExample example);

    TabSalesmanTask selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabSalesmanTask record, @Param("example") TabSalesmanTaskExample example, @Param("selective") TabSalesmanTask.Column ... selective);

    int updateByExample(@Param("record") TabSalesmanTask record, @Param("example") TabSalesmanTaskExample example);

    int updateByPrimaryKeySelective(@Param("record") TabSalesmanTask record, @Param("selective") TabSalesmanTask.Column ... selective);

    int updateByPrimaryKey(TabSalesmanTask record);

    int batchInsert(@Param("list") List<TabSalesmanTask> list);

    int batchInsertSelective(@Param("list") List<TabSalesmanTask> list, @Param("selective") TabSalesmanTask.Column ... selective);
}