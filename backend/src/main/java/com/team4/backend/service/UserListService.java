package com.team4.backend.service;

import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.dto.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListService {

    @Autowired
    private RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;

    public List<UserListDto> getUserList() {
        return redisToMariaDBMigrationMapper.getUserListFromDB();
    }
}