package com.autuan.project.promote.group.mapper;

import com.autuan.project.promote.group.domain.TabGroup;
import com.autuan.project.promote.group.domain.TabGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabGroupMapper {
    long countByExample(TabGroupExample example);

    int deleteByExample(TabGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabGroup record);

    int insertSelective(@Param("record") TabGroup record, @Param("selective") TabGroup.Column ... selective);

    TabGroup selectOneByExample(TabGroupExample example);

    List<TabGroup> selectByExample(TabGroupExample example);

    TabGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabGroup record, @Param("example") TabGroupExample example, @Param("selective") TabGroup.Column ... selective);

    int updateByExample(@Param("record") TabGroup record, @Param("example") TabGroupExample example);

    int updateByPrimaryKeySelective(@Param("record") TabGroup record, @Param("selective") TabGroup.Column ... selective);

    int updateByPrimaryKey(TabGroup record);

    int batchInsert(@Param("list") List<TabGroup> list);

    int batchInsertSelective(@Param("list") List<TabGroup> list, @Param("selective") TabGroup.Column ... selective);
}