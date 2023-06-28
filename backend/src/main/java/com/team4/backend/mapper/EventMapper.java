package com.team4.backend.mapper;

import com.team4.backend.model.dto.EventDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {


    @Insert("insert into event(estart, end, title) values(#{start},#{end},#{title})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveEvent(EventDTO event);


    @Select("SELECT id, estart, end, title FROM event WHERE month(estart) = #{year} OR month(end) = #{year}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "estart", property = "start"),
            @Result(column = "end", property = "end"),
            @Result(column = "title", property = "title")
    })
    List<EventDTO> listMonthly(int year);
}
