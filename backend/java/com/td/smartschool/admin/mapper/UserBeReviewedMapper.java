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
 * @date 2022年4月18日 22:49:35
 */
@Mapper
public interface UserBeReviewedMapper {

    @Select("select account, name, department, reason from user.bereviewed")
    List<User> getUsers();

    @Select("select account, name, department, reason from user.bereviewed where account = #{username}")
    User getUser(String username);

    @Insert("insert into user.bereviewed(account, name, department, reason) values(#{account}, #{name}, #{department}, #{reason})")
    int addUser(User user);

    @Delete("delete from user.bereviewed where account = #{username}")
    int deleteUser(String username);
}
