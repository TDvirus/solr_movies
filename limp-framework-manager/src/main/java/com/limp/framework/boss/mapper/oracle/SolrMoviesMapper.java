package com.limp.framework.boss.mapper.oracle;

import com.limp.framework.boss.domain.SolrMovies;
import com.limp.framework.boss.domain.SolrMoviesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SolrMoviesMapper {
    int countByExample(SolrMoviesExample example);

    int deleteByExample(SolrMoviesExample example);

    int deleteByPrimaryKey(String id);

    int insert(SolrMovies record);

    int insertSelective(SolrMovies record);

    List<SolrMovies> selectByExample(SolrMoviesExample example);

    SolrMovies selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SolrMovies record, @Param("example") SolrMoviesExample example);

    int updateByExample(@Param("record") SolrMovies record, @Param("example") SolrMoviesExample example);

    int updateByPrimaryKeySelective(SolrMovies record);

    int updateByPrimaryKey(SolrMovies record);
}