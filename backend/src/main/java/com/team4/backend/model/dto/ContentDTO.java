package com.team4.backend.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private Long id;
    private Long writer_id;
    private String title;
    private String content;
    private Date uploadDate;

}
