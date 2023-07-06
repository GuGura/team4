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

    @Select("SELECT f1.FRIEND_RECEIVER, m.USERNAME, m.USER_ICON_URL\n" +
            "FROM friend f1\n" +
            "         JOIN member m ON f1.FRIEND_RECEIVER = m.ID\n" +
            "WHERE f1.FRIEND_SENDER = #{memberUID} \n" +
            "  AND f1.FRIEND_CHECKED = true\n" +
            "  AND EXISTS ( \n" +
            "    SELECT #{memberUID} \n" +
            "    FROM friend f2\n" +
            "    WHERE f2.FRIEND_SENDER = f1.FRIEND_RECEIVER\n" +
            "      AND f2.FRIEND_RECEIVER = f1.FRIEND_SENDER\n" +
            "      AND f2.FRIEND_CHECKED = true \n" +
            ")")
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
