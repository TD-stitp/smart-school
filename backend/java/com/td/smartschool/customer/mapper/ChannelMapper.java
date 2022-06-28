package com.td.smartschool.customer.mapper;


import com.td.smartschool.customer.pojo.Channel;
import com.td.smartschool.customer.pojo.Name;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月26日 11:57:57
 */
@Mapper
public interface ChannelMapper {

    @Select("select code, name, createtime from channels.${tableName}")
    List<Channel> getChannels(String tableName);

    @Insert("insert into channels.${tableName}(code, name, createtime) values(#{code}, #{name}, #{createtime})")
    int addChannel(String tableName, String code, String name, Date createtime);

    @Update("update channels.${tableName} set name = #{name} where code = #{code}")
    int setChannelName(String tableName, String code, String name);

    @Delete("delete from channels.${tableName} where code = #{code}")
    int deleteChannel(String tableName, String code);

    @Insert("insert into channels.names(code, name) values(#{code}, #{name})")
    int addName(Name name);

    @Select("select name from channels.names where code = #{code}")
    String getName(String code);

    @Update("update channels.names set name = #{name} where code = #{code}")
    int setName(String code, String name);

    @Delete("delete from channels.names where code = #{code}")
    int deleteName(String code);
}
