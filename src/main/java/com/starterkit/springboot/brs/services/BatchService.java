package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.dto.MentorDTO;
import com.starterkit.springboot.brs.dto.RegisterToBatchDTO;
import com.starterkit.springboot.brs.dto.TeckerDTO;
import com.starterkit.springboot.brs.models.Batch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BatchService {

    Batch save(Batch batch);

    List<Batch> list();

    Optional<Batch> findById(UUID id);

    List<Batch> findByProgram(UUID programId);

    void delete(UUID id);

    void registerUserToBatch(UUID batchId, UUID userId);

    void registerToBatch(List<UUID> usersToRegister, UUID batchId);

    void unregisterToBatch(List<UUID> usersToUnregister, UUID batchId);

    void registerMultipleUsersToBatch(RegisterToBatchDTO register);

    List<MentorDTO> getMentorsByBatch(UUID batchId);

    List<TeckerDTO> getTeckersByBatch(UUID batchId);

}
