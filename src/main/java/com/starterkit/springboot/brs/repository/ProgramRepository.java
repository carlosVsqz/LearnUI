package com.starterkit.springboot.brs.repository;

import java.util.UUID;

import com.starterkit.springboot.brs.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, UUID> {
}
