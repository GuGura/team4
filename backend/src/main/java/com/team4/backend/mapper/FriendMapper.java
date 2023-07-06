package com.team4.backend.mapper;


import com.team4.backend.model.dto.FriendDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {


    @Select("SELECT * FROM FRIEND WHERE FRIEND_SENDER= #{friend_SENDER} AND FRIEND_CHECKED='1'")
    List<FriendDTO> findAll();


}

