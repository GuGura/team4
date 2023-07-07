package com.team4.backend.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListDto {

    public UserListDto() {
    }

    @Builder
    public UserListDto(String id, String username, String channelUid, String message) {
        this.userUID = id;
        this.userName = username;
        this.channelUID = channelUid;
        this.message = message;
    }

    private String userUID;
    private String userName;
    private String channelUID;
    private String message;
}
