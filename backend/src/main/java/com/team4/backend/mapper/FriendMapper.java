package com.team4.backend.mapper;

import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.FriendDTO2;
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

    @Select("SELECT ID, USERNAME, USER_ICON_URL " +
            "FROM member " +
            "WHERE USERNAME LIKE '%' #{username} '%' " +
            "  AND ID NOT IN (SELECT FRIEND_RECEIVER " +
            "                 FROM friend " +
            "                 WHERE FRIEND_SENDER = #{memberUID} ) " +
            "AND ID != #{memberUID} ")
    List<FriendDTO2> findSearchUsers(@Param("username") String username, @Param("memberUID") int memberUID);
}
