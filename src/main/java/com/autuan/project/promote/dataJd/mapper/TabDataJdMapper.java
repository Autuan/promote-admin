package com.autuan.project.promote.dataJd.mapper;

import com.autuan.project.promote.dataJd.domain.TabDataJd;
import com.autuan.project.promote.dataJd.domain.TabDataJdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabDataJdMapper {
    long countByExample(TabDataJdExample example);

    int deleteByExample(TabDataJdExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabDataJd record);

    int insertSelective(@Param("record") TabDataJd record, @Param("selective") TabDataJd.Column ... selective);

    TabDataJd selectOneByExample(TabDataJdExample example);

    List<TabDataJd> selectByExample(TabDataJdExample example);

    TabDataJd selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabDataJd record, @Param("example") TabDataJdExample example, @Param("selective") TabDataJd.Column ... selective);

    int updateByExample(@Param("record") TabDataJd record, @Param("example") TabDataJdExample example);

    int updateByPrimaryKeySelective(@Param("record") TabDataJd record, @Param("selective") TabDataJd.Column ... selective);

    int updateByPrimaryKey(TabDataJd record);

    int batchInsert(@Param("list") List<TabDataJd> list);

    int batchInsertSelective(@Param("list") List<TabDataJd> list, @Param("selective") TabDataJd.Column ... selective);
}