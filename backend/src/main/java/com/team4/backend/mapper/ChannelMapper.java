package com.team4.backend.mapper;

import com.team4.backend.model.ChannelMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelMapper {

    @Select("SELECT * FROM channelmember where MEMBER_UID = #{memberUID} ")
    List<ChannelMember> findChannelsByEmail(@Param("memberUID") int memberUID);
}
