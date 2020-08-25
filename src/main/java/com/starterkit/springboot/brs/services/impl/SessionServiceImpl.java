package com.starterkit.springboot.brs.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.starterkit.springboot.brs.exception.SessionCannotBeDeletedException;
import com.starterkit.springboot.brs.exception.UserAlreadyConfirmedException;
import com.starterkit.springboot.brs.models.Batch;
import com.starterkit.springboot.brs.models.Session;
import com.starterkit.springboot.brs.models.User;
import com.starterkit.springboot.brs.repository.SessionRepository;
import com.starterkit.springboot.brs.repository.UserRepository;
import com.starterkit.springboot.brs.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;

    private UserRepository userRepository;

    @Override
    @Transactional
    public Session save(Session session) {
        if (Objects.isNull(session.getId())) {
            session.setId(UUID.randomUUID());
        }
        return sessionRepository.save(session);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> allPeople(UUID sessionId) {
        return userRepository.allPeopleBySession(sessionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> staff(UUID sessionId) {
        return userRepository.staffBySession(sessionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @Override
    @Transactional
    public void confirmAttendance(UUID sessionId, UUID userId) {
        userRepository.getUserBySession(sessionId, userId).ifPresentOrElse(
                user -> {
                    throw new UserAlreadyConfirmedException(user.getId() + " has been confirmed already");
                },
                () -> sessionRepository.confirmAttendance(sessionId, userId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Session> findById(UUID id) {
        return sessionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> findByBatch(UUID batchId) {
        Batch batch = new Batch();

        batch.setId(batchId);

        return sessionRepository.findByBatch(batch);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<Session> optionalSession = sessionRepository.findById(id);

        optionalSession.ifPresent(
                session -> sessionRepository.doesSessionHaveUsersConfirmed(session.getId()).ifPresentOrElse(
                        doHaveUsers -> {
                            throw new SessionCannotBeDeletedException(session.getId() + " can't delete the session");
                        }, () -> sessionRepository.delete(session))
        );
    }

    @Autowired
    public void setSessionRepository(SessionRepository batchRepository) {
        this.sessionRepository = batchRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
