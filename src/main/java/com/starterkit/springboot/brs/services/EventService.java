package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.dto.EventDTO;

import java.util.List;
import java.util.UUID;


public interface EventService {

    List<EventDTO> listEvents(int year, int month, UUID userId);
}
