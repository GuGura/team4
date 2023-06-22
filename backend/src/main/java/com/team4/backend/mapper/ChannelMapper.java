package com.team4.backend.mapper;

import com.team4.backend.model.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelMapper {

    @Select("SELECT * FROM channel where ")
    List<Channel> findChannelsByEmail(int userUID);
}
