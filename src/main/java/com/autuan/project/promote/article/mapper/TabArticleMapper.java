package com.autuan.project.promote.article.mapper;

import com.autuan.project.promote.article.domain.TabArticle;
import com.autuan.project.promote.article.domain.TabArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabArticleMapper {
    long countByExample(TabArticleExample example);

    int deleteByExample(TabArticleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabArticle record);

    int insertSelective(@Param("record") TabArticle record, @Param("selective") TabArticle.Column ... selective);

    TabArticle selectOneByExample(TabArticleExample example);

    TabArticle selectOneByExampleWithBLOBs(TabArticleExample example);

    List<TabArticle> selectByExampleWithBLOBs(TabArticleExample example);

    List<TabArticle> selectByExample(TabArticleExample example);

    TabArticle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabArticle record, @Param("example") TabArticleExample example, @Param("selective") TabArticle.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") TabArticle record, @Param("example") TabArticleExample example);

    int updateByExample(@Param("record") TabArticle record, @Param("example") TabArticleExample example);

    int updateByPrimaryKeySelective(@Param("record") TabArticle record, @Param("selective") TabArticle.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(TabArticle record);

    int updateByPrimaryKey(TabArticle record);

    int batchInsert(@Param("list") List<TabArticle> list);

    int batchInsertSelective(@Param("list") List<TabArticle> list, @Param("selective") TabArticle.Column ... selective);
}