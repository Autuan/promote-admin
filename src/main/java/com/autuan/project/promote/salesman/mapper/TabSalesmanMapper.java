package com.autuan.project.promote.salesman.mapper;

import com.autuan.project.promote.salesman.domain.TabSalesman;
import com.autuan.project.promote.salesman.domain.TabSalesmanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabSalesmanMapper {
    long countByExample(TabSalesmanExample example);

    int deleteByExample(TabSalesmanExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabSalesman record);

    int insertSelective(@Param("record") TabSalesman record, @Param("selective") TabSalesman.Column ... selective);

    TabSalesman selectOneByExample(TabSalesmanExample example);

    List<TabSalesman> selectByExample(TabSalesmanExample example);

    TabSalesman selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabSalesman record, @Param("example") TabSalesmanExample example, @Param("selective") TabSalesman.Column ... selective);

    int updateByExample(@Param("record") TabSalesman record, @Param("example") TabSalesmanExample example);

    int updateByPrimaryKeySelective(@Param("record") TabSalesman record, @Param("selective") TabSalesman.Column ... selective);

    int updateByPrimaryKey(TabSalesman record);

    int batchInsert(@Param("list") List<TabSalesman> list);

    int batchInsertSelective(@Param("list") List<TabSalesman> list, @Param("selective") TabSalesman.Column ... selective);
}