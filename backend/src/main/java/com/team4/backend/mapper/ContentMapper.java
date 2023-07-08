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

    @Select("SELECT * FROM content WHERE (writer_id = " +
            "(SELECT f1.FRIEND_RECEIVER FROM friend f1 " +
            "JOIN member m ON f1.FRIEND_RECEIVER = m.ID " +
            "WHERE f1.FRIEND_SENDER = #{memberUID} " +
            "AND f1.FRIEND_CHECKED = true " +
            "AND EXISTS " +
            "(SELECT #{memberUID} FROM friend f2 " +
            "WHERE f2.FRIEND_SENDER = f1.FRIEND_RECEIVER " +
            "AND f2.FRIEND_RECEIVER = f1.FRIEND_SENDER " +
            "AND f2.FRIEND_CHECKED = true) " +
            "LIMIT 1) or writer_id = #{memberUID})" +
            "AND id < #{pageNum} ORDER BY id DESC LIMIT 10")
    List<ContentDTO> listContentFeed(@Param("pageNum")int pageNum, @Param("memberUID") int memberUID);
}
