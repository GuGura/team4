package com.team4.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO {
    private int id;
    private String userName;
    private String nickName;
    private String user_description;
    private String icon_URL;
}
