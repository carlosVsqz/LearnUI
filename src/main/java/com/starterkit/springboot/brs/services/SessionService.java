package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.models.Session;
import com.starterkit.springboot.brs.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SessionService {
    Session save(Session session);

    List<Session> list();

    Optional<Session> findById(UUID id);

    List<Session> findByBatch(UUID batchId);

    void confirmAttendance(UUID sessionId, UUID userId);

    void delete(UUID id);

    List<User> allPeople(UUID sessionId);

    List<User> staff(UUID sessionId);


}
