package com.team4.backend.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private int id;
    private int writer_id;
    private String title;
    private String content;
    private Date uploadDate;
    private boolean visible;
    private String contentIMG;
    private boolean isImgIn;
    private int sharingCode;
    private int sharedWriter;
}
