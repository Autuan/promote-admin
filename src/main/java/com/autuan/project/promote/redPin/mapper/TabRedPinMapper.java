package com.autuan.project.promote.redPin.mapper;

import com.autuan.project.promote.redPin.domain.TabRedPin;
import com.autuan.project.promote.redPin.domain.TabRedPinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabRedPinMapper {
    long countByExample(TabRedPinExample example);

    int deleteByExample(TabRedPinExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabRedPin record);

    int insertSelective(@Param("record") TabRedPin record, @Param("selective") TabRedPin.Column ... selective);

    TabRedPin selectOneByExample(TabRedPinExample example);

    List<TabRedPin> selectByExample(TabRedPinExample example);

    TabRedPin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabRedPin record, @Param("example") TabRedPinExample example, @Param("selective") TabRedPin.Column ... selective);

    int updateByExample(@Param("record") TabRedPin record, @Param("example") TabRedPinExample example);

    int updateByPrimaryKeySelective(@Param("record") TabRedPin record, @Param("selective") TabRedPin.Column ... selective);

    int updateByPrimaryKey(TabRedPin record);

    int batchInsert(@Param("list") List<TabRedPin> list);

    int batchInsertSelective(@Param("list") List<TabRedPin> list, @Param("selective") TabRedPin.Column ... selective);
}