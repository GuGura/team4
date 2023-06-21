package com.team4.backend.service;

import com.team4.backend.mapper.EventMapper;
import com.team4.backend.model.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventMapper eventMapper;


    public List<EventDTO> listMonthly(int year) {
        return eventMapper.listMonthly(year);
    }

    public Long saveEvent(EventDTO event) {
        System.out.println(event.getStart());
        eventMapper.saveEvent(event);
        Long id = event.getId();
        return id;
    }
}
