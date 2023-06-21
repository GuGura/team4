package com.team4.backend.service;

import com.team4.backend.mapper.UserDetailMapper;
import com.team4.backend.model.dto.UserDetailDTO;
import com.team4.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserDetailMapper userDetailMapper;

    public void updateProfile(UserDetailDTO userDetail) {
        userDetailMapper.updateProfile(userDetail);
    }

    public void createProfile(UserDetailDTO userDetail){
        userDetailMapper.createProfile(userDetail);
    }

    public void updateIcon(String IconURL) { userDetailMapper.updateIcon_URL(IconURL, UserUtil.getEmail());
    }
}
