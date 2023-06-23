package com.team4.backend.mapper;

import com.team4.backend.model.ChannelMember;
import com.team4.backend.model.dto.MyChannelsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelMapper {

    @Select("select m.CHANNEL_UID, m.MEMBER_UID, c.CHANNEL_TITLE, c.CHANNEL_ICON_URL " +
            "from channelmember m " +
            "left join channel c on m.CHANNEL_UID = c.CHANNEL_UID " +
            "where m.MEMBER_UID = #{memberUID}")
    List<MyChannelsDTO> findChannelsByMemberUID(@Param("memberUID") int memberUID);
}
//select m.CHANNEL_UID, m.MEMBER_UID, c.CHANNEL_TITLE, c.CHANNEL_ICON_URL
//from channelmember m
//left join channel c on m.CHANNEL_UID = c.CHANNEL_UID
//where m.MEMBER_UID = 3