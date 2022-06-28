package com.td.smartschool.admin.mapper;


import com.td.smartschool.admin.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年4月15日 20:48:00
 */
@Mapper
public interface AdminMapper {

    @Select("select nickname, account, password, permission from admin.admins")
    List<Admin> getAdmins();

    @Select("select nickname, account, password, permission from admin.admins where account = #{account}")
    Admin getAdmin(String account);

    @Update("update admin.admins set nickname = #{nickname}, password = #{password}, permission = #{permission} where account = #{account}")
    int updateAdmin(Admin admin);

    @Delete("delete from admin.admins where account = #{account}")
    int deleteAdmin(String account);

    @Insert("insert into admin.admins(nickname, account, password, permission) values(#{nickname}, #{account}, #{password}, #{permission})")
    int addAdmin(Admin admin);

}
