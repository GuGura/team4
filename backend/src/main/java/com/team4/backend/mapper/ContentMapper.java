package com.team4.backend.mapper;

import com.team4.backend.model.dto.ContentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContentMapper {

    @Select("select * from content where writer_id = #{userUID} and id < #{pageNum} order by id desc limit 20")
    List<ContentDTO> listContent(int pageNum,int userUID);

    @Insert("insert into content(id, content, uploadDate) values(seq_content.nextVal, #{content}, sysdate )")
    void saveContent(String content);

    @Select("select * from content ")
    List<ContentDTO> findAll();
}
