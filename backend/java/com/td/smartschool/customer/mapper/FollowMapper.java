package com.td.smartschool.customer.mapper;

import com.td.smartschool.customer.pojo.Follow;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月24日 10:48:08
 */
@Mapper
public interface FollowMapper {

    @Select("select id, code, lastmessage from follows.${tableName}")
    List<Follow> getFollows(String tableName);

    @Select("select id, code, lastmessage from follows.${tableName} where id = #{code}")
    Follow getLastFollow(String tableName, String code);

    @Select("select count(id) from follows.${tableName}")
    int getFollowNumber(String tableName);

    @Insert("insert into follows.${tableName}(code, lastmessage) values(#{code}, #{lastmessage})")
    int addFollow(String tableName, String code, @Param("lastmessage") String lastMessage);

    @Update("update follows.${tableName} set lastmessage = #{lastmessage} where code = #{code}")
    int setFollow(String tableName, String code, @Param("lastmessage") String lastMessage);

    @Delete("delete from follows.${tableName} where code = #{code}")
    int deleteFollow(String tableName, String code);
}
