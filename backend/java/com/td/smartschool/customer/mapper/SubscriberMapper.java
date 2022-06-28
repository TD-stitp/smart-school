package com.td.smartschool.customer.mapper;


import com.td.smartschool.customer.pojo.Subscriber;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月24日 15:57:54
 */
@Mapper
public interface SubscriberMapper {

    @Select("select id, studentID from subscribers.${tableName}")
    List<Subscriber> getSubscribers(String tableName);

    @Select("select count(id) from subscribers.${tableName}")
    int getDataRows(String tableName);

    @Insert("insert into subscribers.${tableName}(studentID) values (#{studentID})")
    int addSubscriber(String tableName, String studentID);

    @Delete("delete from subscribers.${tableName} where studentID=#{studentID}")
    int deleteSubscriber(String tableName, String studentID);
}
