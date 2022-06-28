package com.td.smartschool.admin.mapper;


import com.td.smartschool.admin.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年4月16日 21:59:54
 */
@Mapper
public interface BeReviewedMapper {

    @Select("select nickname, account, password from admin.bereviewed")
    List<Admin> getBeRevieweds();

    @Select("select nickname, account, password from admin.bereviewed where account = #{account}")
    Admin getBeReviewed(String account);

    @Update("update admin.bereviewed set nickname = #{nickname}, password = #{password} where account = #{account}")
    int updateBeReviewed(Admin admin);

    @Delete("delete from admin.bereviewed where account = #{account}")
    int deleteBeReviewed(String account);

    @Insert("insert into admin.bereviewed(nickname, account, password) values(#{nickname}, #{account}, #{password})")
    int addBeReviewed(Admin admin);
}
