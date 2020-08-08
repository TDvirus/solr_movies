package com.limp.framework.boss.mapper.oracle;

import com.limp.framework.boss.domain.DicCode;
import com.limp.framework.boss.domain.DicCodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicCodeMapper {
    int countByExample(DicCodeExample example);

    int deleteByExample(DicCodeExample example);

    int deleteByPrimaryKey(String id);

    int insert(DicCode record);

    int insertSelective(DicCode record);

    List<DicCode> selectByExample(DicCodeExample example);

    DicCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DicCode record, @Param("example") DicCodeExample example);

    int updateByExample(@Param("record") DicCode record, @Param("example") DicCodeExample example);

    int updateByPrimaryKeySelective(DicCode record);

    int updateByPrimaryKey(DicCode record);
}