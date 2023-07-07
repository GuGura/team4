package com.team4.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ResultFriend {
    private int ID;
    private String USERNAME;
    private byte[] USER_ICON_URL;
}
