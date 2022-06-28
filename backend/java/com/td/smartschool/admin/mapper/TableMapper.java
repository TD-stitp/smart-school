package com.td.smartschool.admin.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2022年2月21日 21:09:15
 */
@Mapper
public interface TableMapper {

    /**
     *
     */
    @Update("CREATE TABLE IF NOT EXISTS messages.`${tableName}`  (" +
            "`id` int NOT NULL AUTO_INCREMENT," +
            "`date` varchar(255)," +
            "`message` varchar(255)," +
            "PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
    int createMessages(String tableName);

    @Update("CREATE TABLE IF NOT EXISTS subscribers.`${tableName}`  (" +
            "`id` int NOT NULL AUTO_INCREMENT," +
            "`studentID` varchar(255)," +
            "PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;")
    int createSubscribers(String tableName);

    @Update("CREATE TABLE IF NOT EXISTS follows.`${tableName}`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `code` varchar(255) NULL," +
            "  `lastmessage` varchar(255)," +
            "  PRIMARY KEY (`id`)" +
            ");")
    int createFollows(String tableName);

    @Update("CREATE TABLE IF NOT EXISTS channels.`${tableName}`  (" +
            "  `id` int NOT NULL AUTO_INCREMENT," +
            "  `code` varchar(255) NULL," +
            "  `name` varchar(255) NULL," +
            "  `createtime` varchar(255)," +
            "  PRIMARY KEY (`id`)" +
            ");")
    int createChannels(String tableName);

    @Delete("DROP TABLE ${databaseName}.${tableName}")
    int dropDatabase(String databaseName, String tableName);

    @Select("select count(*) from ${type}.${tableName}")
    int getDataRows(String type, String tableName);

}
