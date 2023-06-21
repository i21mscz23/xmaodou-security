package com.xmd.user.mapper;


import com.xmd.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from sys_user where delete_status = 1 and username = #{username}")
    User queryByUsername(@Param("username")String username);

}
