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


    public List<EventDTO> listMonthly(int year, int memberId) {
        return eventMapper.listMonthly(year, memberId);
    }

    public int saveEvent(EventDTO event) {
        eventMapper.saveEvent(event);
        return eventMapper.selectLast();
    }

    public void deleteEvent(int id) {
        eventMapper.deleteEvent(id);
    }
}
