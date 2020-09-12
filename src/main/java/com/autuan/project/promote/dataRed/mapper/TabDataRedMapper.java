package com.autuan.project.promote.dataRed.mapper;

import com.autuan.project.promote.dataRed.domain.TabDataRed;
import com.autuan.project.promote.dataRed.domain.TabDataRedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabDataRedMapper {
    long countByExample(TabDataRedExample example);

    int deleteByExample(TabDataRedExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabDataRed record);

    int insertSelective(@Param("record") TabDataRed record, @Param("selective") TabDataRed.Column ... selective);

    TabDataRed selectOneByExample(TabDataRedExample example);

    List<TabDataRed> selectByExample(TabDataRedExample example);

    TabDataRed selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabDataRed record, @Param("example") TabDataRedExample example, @Param("selective") TabDataRed.Column ... selective);

    int updateByExample(@Param("record") TabDataRed record, @Param("example") TabDataRedExample example);

    int updateByPrimaryKeySelective(@Param("record") TabDataRed record, @Param("selective") TabDataRed.Column ... selective);

    int updateByPrimaryKey(TabDataRed record);

    int batchInsert(@Param("list") List<TabDataRed> list);

    int batchInsertSelective(@Param("list") List<TabDataRed> list, @Param("selective") TabDataRed.Column ... selective);
}