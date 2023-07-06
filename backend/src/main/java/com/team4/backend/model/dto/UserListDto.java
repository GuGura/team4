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
        this.id = id;
        this.username = username;
        this.channelUid = channelUid;
        this.message = message;
    }

    private String id;
    private String username;
    private String channelUid;
    private String message;
}
