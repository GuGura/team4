package com.team4.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    private int channel_id;
    private String channel_title;
    private String channel_icon_url;
    private String channel_description;
    private int channel_owner;
    private String channel_inviteCode;
    private boolean channel_is_Open;
    private Date channel_createDate;
}
