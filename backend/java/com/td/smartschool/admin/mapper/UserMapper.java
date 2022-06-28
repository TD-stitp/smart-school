package com.td.smartschool.admin.mapper;

import com.td.smartschool.admin.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月25日 17:18:52
 */
@Mapper
public interface UserMapper {

    @Select("select account, name, department from user.users")
    List<User> getUsers();

    @Select("select account, name, department from user.users where account = #{username}")
    User getUser(String username);

    @Insert("insert into user.users(account, name, department) values(#{account}, #{name}, #{department})")
    int addUser(User user);

    @Delete("delete from user.users where account = #{username}")
    int deleteUser(String username);
}
