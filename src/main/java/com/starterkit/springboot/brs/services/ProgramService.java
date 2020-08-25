package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.models.Program;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProgramService {

    Program save(Program program);

    List<Program> list();

    Optional<Program> findById(UUID id);

    Optional<Program> delete(UUID fromString);
}
