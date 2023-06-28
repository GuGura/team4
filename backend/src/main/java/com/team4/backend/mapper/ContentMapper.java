package com.team4.backend.mapper;

import com.team4.backend.model.dto.ContentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContentMapper {

    @Select("select * from content where writer_id = #{userUID} and id < #{pageNum} order by id desc limit 10")
    List<ContentDTO> listContent(int pageNum,int userUID);

    @Insert("insert into content(writer_id ,title,  content, contentIMG, isImgIn) values(#{writer_id},#{title}, #{content}, #{contentIMG}, #{isImgIn})")
    void saveContent(ContentDTO content);

    @Select("select * from content ")
    List<ContentDTO> findAll();
}
