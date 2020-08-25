package com.starterkit.springboot.brs.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.starterkit.springboot.brs.dto.EventDTO;
import com.starterkit.springboot.brs.models.Session;
import com.starterkit.springboot.brs.repository.SessionRepository;
import com.starterkit.springboot.brs.services.EventService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private static final String TYPE_SESSION = "SESSION";
    private SessionRepository sessionRepository;
    private ModelMapper modelMapper;

    @Override
    public List<EventDTO> listEvents(int year, int month, UUID userId) {

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid Month");
        }

        List<Session> sessions = sessionRepository.getSessionsByUserByDate(year, month, userId);
        return sessions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostConstruct
    public void prepareMappings() {

        modelMapper.addMappings(new PropertyMap<Session, EventDTO>() {
            @Override
            protected void configure() {
                map().setType(TYPE_SESSION);
                map().setSubject(source.getTitle());
                map().setDirections(source.getNotes());
                map().setDate(source.getDate());
            }
        });
    }

    private EventDTO convertToDTO(Session session) {

        return modelMapper.map(session, EventDTO.class);
    }

    @Autowired
    public void setSessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


}
