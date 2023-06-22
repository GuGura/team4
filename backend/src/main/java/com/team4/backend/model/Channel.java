package com.team4.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    private int id;
    private String title;
    private String description;
    private int owner;
    private String inviteCode;
    private boolean isOpen;
    private Date createDate;
}
