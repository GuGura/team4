package com.team4.backend.mapper;

import com.team4.backend.model.dto.ContentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContentMapper {

    @Select("select * from content where writer_id = #{userUID} and id < #{pageNum} order by id desc limit 10")
    List<ContentDTO> listContent(@Param("pageNum") int pageNum, @Param("userUID") int userUID);

    @Insert("insert into content(writer_id ,title,  content, contentIMG, isImgIn, sharingCode, sharedWriter) values(#{content.writer_id},#{content.title}, #{content.content}, #{content.contentIMG}, #{content.isImgIn}, #{content.sharingCode}, #{content.sharedWriter})")
    void saveContent(@Param("content") ContentDTO content);

    @Select("select * from content ")
    List<ContentDTO> findAll();

    @Select("select * from content where id = #{id}")
    ContentDTO getContentById(@Param("id") int id);

    @Delete("delete from content where id = #{id} and (writer_id = #{writerId} or sharedWriter = #{writerId})")
    void deleteContent(int id, int writerId);
}
