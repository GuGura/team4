package com.team4.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private Date start;
    private Date end;
    private String title;
    private Long memberId;
    private Long groupId;
    private boolean allDay;
}
