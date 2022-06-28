package com.td.smartschool.admin.mapper;

import com.td.smartschool.admin.pojo.Img;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author XX
 * @date 2021/11/4 16:54
 */
@Mapper
public interface ImageMapper {

    @Select("select subscript, uuid, imagename, title, img from image where uuid=#{uuid}")
    Img select(String uuid);

    @Select("select subscript,uuid,imagename,title from image")
    List<Img> getUUIDs();

    @Select("select max(subscript) from image")
    int getMaxSubscript();

    @Update("update image set subscript = #{subscript} where uuid = #{uuid}")
    int setSubscript(Img img);

    @Update("update image set title = #{title} where uuid = #{uuid}")
    int setTitle(Img img);

    @Insert("insert into image(imagename, subscript, title, img, uuid) values(#{imagename},#{subscript}, #{title}, #{img}, #{uuid})")
    int insert(Img img);


}
