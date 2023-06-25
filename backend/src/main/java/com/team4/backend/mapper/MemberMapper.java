package com.team4.backend.mapper;

import com.team4.backend.model.Member;
import com.team4.backend.model.dto.MemberDTO;
import com.team4.backend.model.User;
import com.team4.backend.model.dto.MyChannelsDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member (EMAIL,USERNAME,PASSWORD) VALUES(#{email},#{username},#{password})")
    void save(MemberDTO memberDTO);

    @Select("SELECT * FROM member WHERE EMAIL = #{isEmail}")
    Optional<MemberDTO> findMemberByEmail(@Param("isEmail") String isEmail);

    @Select("SELECT * FROM member WHERE EMAIL = #{username}")
    Optional<User> findUsername(String username);

    @Select("SELECT * FROM member WHERE EMAIL = #{email}")
    Optional<User> findUserByEmail(String email);

    @Select("SELECT EMAIL, USERNAME, ROLE, JOIN_DATE, USER_ICON_URL, USER_DESCRIPTION " +
            "FROM member WHERE ID = #{memberUID}")
    Member findMemberByUID(int memberUID);
}
