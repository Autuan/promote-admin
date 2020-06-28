package com.autuan.project.promote.dataBank.mapper;

import com.autuan.project.promote.dataBank.domain.TabDataBank;
import com.autuan.project.promote.dataBank.domain.TabDataBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabDataBankMapper {
    long countByExample(TabDataBankExample example);

    int deleteByExample(TabDataBankExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabDataBank record);

    int insertSelective(@Param("record") TabDataBank record, @Param("selective") TabDataBank.Column ... selective);

    TabDataBank selectOneByExample(TabDataBankExample example);

    List<TabDataBank> selectByExample(TabDataBankExample example);

    TabDataBank selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabDataBank record, @Param("example") TabDataBankExample example, @Param("selective") TabDataBank.Column ... selective);

    int updateByExample(@Param("record") TabDataBank record, @Param("example") TabDataBankExample example);

    int updateByPrimaryKeySelective(@Param("record") TabDataBank record, @Param("selective") TabDataBank.Column ... selective);

    int updateByPrimaryKey(TabDataBank record);

    int batchInsert(@Param("list") List<TabDataBank> list);

    int batchInsertSelective(@Param("list") List<TabDataBank> list, @Param("selective") TabDataBank.Column ... selective);
}