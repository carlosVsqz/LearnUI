package com.starterkit.springboot.brs.controller;

import java.security.Principal;
import java.util.List;

import com.starterkit.springboot.brs.config.auth.TokenInfoService;
import com.starterkit.springboot.brs.dto.EventDTO;
import com.starterkit.springboot.brs.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/events")
public class EventController {

    private EventService eventService;

    private TokenInfoService tokenInfoService;

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<EventDTO>> listEvents(@PathVariable int month, @PathVariable int year, Principal principal) {

        return new ResponseEntity<>(eventService.listEvents(year, month, tokenInfoService.getIdFromPrincipal(principal)), HttpStatus.OK);
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setTokenInfoService(TokenInfoService tokenInfoService) {
        this.tokenInfoService = tokenInfoService;
    }
}
