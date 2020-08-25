package com.starterkit.springboot.brs.repository;

import com.starterkit.springboot.brs.models.TeckerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TeckerAssignmentRepository extends JpaRepository<TeckerAssignment, UUID> {

    @Modifying
    @Query(value = "DELETE FROM tecker_by_deliverable WHERE deliverable_id = :deliverableId", nativeQuery = true)
    void removeAssignment(UUID deliverableId);

    @Query("SELECT ta FROM TeckerAssignment ta WHERE ta.teckerId = :teckerId AND ta.deliverableId = :deliverableId")
    Optional<TeckerAssignment> getTeckerAssigmentByTecker(UUID teckerId, UUID deliverableId);
}
