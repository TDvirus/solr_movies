package com.limp.framework.boss.mapper.oracle;

import com.limp.framework.boss.domain.UserInfo;
import com.limp.framework.boss.domain.UserInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 查询用户list  根据用户 id和roleCode：如果roleCode为空则查询该用户的角色列表
     * @param userId
     * @param roleCode
     * @return
     */
    List<Map<String,String>> selectUsersByUserIdAndRoleCode(@Param("userId") String userId, @Param("roleCode") String roleCode);

}