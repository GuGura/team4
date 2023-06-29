package com.team4.backend.controller;

import com.team4.backend.mapper.MemberMapper;
import com.team4.backend.model.dto.EventDTO;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    private final EventService eventService;
    private final MemberMapper memberMapper;

    @Autowired
    public EventController(EventService eventService, MemberMapper memberMapper) {
        this.eventService = eventService;
        this.memberMapper = memberMapper;
    }

    @PostMapping("api/v1/home/saveEvent")
    public int save(@RequestBody EventDTO event, HttpServletRequest request) throws Exception {
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        event.setGroupName(memberMapper.findMemberByUID(memberUID).getUsername());
        event.setMemberId(memberUID);
        int id = eventService.saveEvent(event);
        return id;
    }

    @PostMapping("api/v1/home/deleteEvent")
    public void deleteEvent(@RequestBody EventDTO event) throws Exception {
        eventService.deleteEvent(event.getId());
    }

    @ResponseBody
    @PostMapping("api/v1/home/listMonthly")
    public ResponseEntity<List<EventDTO>> listMonthly(@RequestBody String date, HttpServletRequest request){
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        String year = date.substring(14,16);
        System.out.println(year);
        List<EventDTO> events= eventService.listMonthly(Integer.parseInt(year), memberUID);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("api/v1/event/listByDate")
    public ResponseEntity<List<EventDTO>> listByDate(@RequestBody Map<String,String> params, HttpServletRequest request){
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        List<EventDTO> events= eventService.listDaily(Integer.parseInt(params.get("year")),Integer.parseInt(params.get("month")), Integer.parseInt(params.get("date")), memberUID);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("api/v1/home/listMonthlyBtn")
    public ResponseEntity<List<EventDTO>> listMonthlyNext(@RequestBody String date, HttpServletRequest request){
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        String year = date.substring(14,16);
        System.out.println(Integer.parseInt(year)+1);
        List<EventDTO> events= eventService.listMonthly(Integer.parseInt(year)+1, memberUID);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


}
