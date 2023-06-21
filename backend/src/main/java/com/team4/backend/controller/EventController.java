package com.team4.backend.controller;

import com.team4.backend.model.dto.EventDTO;
import com.team4.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("api/v1/home/saveEvent")
    public Long save(@RequestBody EventDTO event) throws Exception {
        return eventService.saveEvent(event);
    }

    @ResponseBody
    @PostMapping("api/v1/home/listMonthly")
    public ResponseEntity<List<EventDTO>> listMonthly(@RequestBody String date){
        String year = date.substring(14,16);
        System.out.println(year);
        List<EventDTO> events= eventService.listMonthly(Integer.parseInt(year));
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("api/v1/home/listMonthlyBtn")
    public ResponseEntity<List<EventDTO>> listMonthlyNext(@RequestBody String date){
        String year = date.substring(14,16);
        System.out.println(Integer.parseInt(year)+1);
        List<EventDTO> events= eventService.listMonthly(Integer.parseInt(year)+1);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


}
