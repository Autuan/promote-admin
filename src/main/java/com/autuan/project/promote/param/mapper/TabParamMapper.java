package com.autuan.project.promote.param.mapper;

import com.autuan.project.promote.param.domain.TabParam;
import com.autuan.project.promote.param.domain.TabParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabParamMapper {
    long countByExample(TabParamExample example);

    int deleteByExample(TabParamExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabParam record);

    int insertSelective(@Param("record") TabParam record, @Param("selective") TabParam.Column ... selective);

    TabParam selectOneByExample(TabParamExample example);

    List<TabParam> selectByExample(TabParamExample example);

    TabParam selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabParam record, @Param("example") TabParamExample example, @Param("selective") TabParam.Column ... selective);

    int updateByExample(@Param("record") TabParam record, @Param("example") TabParamExample example);

    int updateByPrimaryKeySelective(@Param("record") TabParam record, @Param("selective") TabParam.Column ... selective);

    int updateByPrimaryKey(TabParam record);

    int batchInsert(@Param("list") List<TabParam> list);

    int batchInsertSelective(@Param("list") List<TabParam> list, @Param("selective") TabParam.Column ... selective);
}