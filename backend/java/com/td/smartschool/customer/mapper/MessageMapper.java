package com.td.smartschool.customer.mapper;


import com.td.smartschool.customer.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月24日 10:36:23
 */
@Mapper
public interface MessageMapper {


    @Select("select id, date, message from messages.${tableName}")
    List<Message> getMessages(String tableName);

    @Select("select date, message from messages.${tableName} where (select max(id) from messages.${tableName})")
    Message getLastMessage(String tableName);

    @Insert("insert into messages.${tableName}(date, message) values(#{date}, #{message})")
    int addMessage(String tableName, String date, String message);

}
