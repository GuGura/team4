package com.team4.backend.mapper;

import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.FriendDTO2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

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

    @Insert("INSERT INTO friend (FRIEND_SENDER,FRIEND_RECEIVER,FRIEND_CHECKED) values (#{memberUID},#{sendMemberUID},true)")
    void saveRequest(@Param("memberUID") int memberUID, @Param("sendMemberUID") int sendMemberUID);

    @Select("SELECT count(*) FROM friend where FRIEND_SENDER=#{memberUID} and FRIEND_RECEIVER=#{sendMemberUID} and FRIEND_CHECKED = true")
    int findData(@Param("memberUID") int memberUID, @Param("sendMemberUID") int sendMemberUID);

    @Insert("INSERT INTO friend (FRIEND_SENDER,FRIEND_RECEIVER) values (#{sendMemberUID},#{memberUID})")
    void saveResponse(@Param("sendMemberUID") int sendMemberUID, @Param("memberUID") int memberUID);
}
