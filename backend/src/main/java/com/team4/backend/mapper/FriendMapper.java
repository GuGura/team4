package com.team4.backend.mapper;

import com.team4.backend.model.FriendDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT f.FRIEND_RECEIVER, m.USERNAME,m.USER_ICON_URL\n" +
            "FROM friend f\n" +
            "left join member m on f.FRIEND_RECEIVER = m.ID\n" +
            "where FRIEND_SENDER = #{memberUID} and FRIEND_CHECKED = true")
    List<FriendDTO> findMyFriendByMemberID(@Param("memberUID") int memberUID);

    @Select("SELECT  f.FRIEND_RECEIVER, m.USERNAME,m.USER_ICON_URL\n" +
            "FROM member m\n" +
            "         left join friend f on m.ID = f.FRIEND_RECEIVER\n" +
            "where USERNAME LIKE '%' #{username} '%'\n" +
            "  and f.FRIEND_CHECKED != true\n" +
            "    and f.FRIEND_SENDER = #{memberUID};")
    List<FriendDTO> findSearchUsers(@Param("username") String username, @Param("memberUID") int memberUID);
}
