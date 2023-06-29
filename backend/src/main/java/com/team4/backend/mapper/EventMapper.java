package com.team4.backend.mapper;

import com.team4.backend.model.dto.EventDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {


    @Insert("insert into event(estart, end, title, memberId, groupName) values(#{start},#{end},#{title}, #{memberId}, #{groupName})")
    void saveEvent(EventDTO event);

    @Select("SELECT last_insert_id();")
    int selectLast();

    @Select("SELECT id, estart, end, title, memberId, groupName from event WHERE month(estart) = #{year} OR month(end) = #{year} and memberId=#{memberId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "estart", property = "start"),
            @Result(column = "end", property = "end"),
            @Result(column = "title", property = "title"),
            @Result(column = "memberId", property = "memberId")
    })
    List<EventDTO> listMonthly(int year, int memberId);


    @Select("SELECT * from EVENT WHERE estart = TO_CHAR(NOW(),'yyyy-mm-dd') || END = TO_CHAR(DATE_ADD(NOW(), INTERVAL 1 DAY),'yyyy-mm-dd') and memberId=#{memberId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "estart", property = "start"),
            @Result(column = "end", property = "end"),
            @Result(column = "title", property = "title"),
            @Result(column = "memberId", property = "memberId"),
            @Result(column = "groupName", property = "groupName")
    })
    List<EventDTO> listDaily(int year,int month,int date, int memberId);

    @Delete("delete from event where id = #{id}")
    void deleteEvent(int id);
}
