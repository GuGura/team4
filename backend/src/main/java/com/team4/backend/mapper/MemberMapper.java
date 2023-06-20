package com.team4.backend.mapper;

import com.team4.backend.model.dto.MemberDTO;
import com.team4.backend.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
