package com.limp.framework.boss.mapper.oracle;

import com.limp.framework.boss.domain.CommandCate;
import com.limp.framework.boss.domain.CommandCateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommandCateMapper {
    int countByExample(CommandCateExample example);

    int deleteByExample(CommandCateExample example);

    int deleteByPrimaryKey(String id);

    int insert(CommandCate record);

    int insertSelective(CommandCate record);

    List<CommandCate> selectByExample(CommandCateExample example);

    CommandCate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CommandCate record, @Param("example") CommandCateExample example);

    int updateByExample(@Param("record") CommandCate record, @Param("example") CommandCateExample example);

    int updateByPrimaryKeySelective(CommandCate record);

    int updateByPrimaryKey(CommandCate record);
}