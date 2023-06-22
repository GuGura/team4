package com.team4.backend.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
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
