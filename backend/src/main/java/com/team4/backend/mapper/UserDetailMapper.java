package com.team4.backend.mapper;

import com.team4.backend.model.Token;
import com.team4.backend.model.dto.UserDetailDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDetailMapper {

    @Update("UPDATE UserDetail SET nickName = #{nickName}, user_description= #{user_description} WHERE userName = #{userName}")
    void updateProfile(UserDetailDTO userDetailDTO);

    @Insert("insert into UserDetail(userName) values(#{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createProfile(UserDetailDTO userDetail);

    @Update("Update UserDetail Set icon_URL = #{iconURL} where userName = #{userName}")
    void updateIcon_URL(String iconURL, String userName);
}
