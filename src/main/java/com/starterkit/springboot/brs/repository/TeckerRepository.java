package com.starterkit.springboot.brs.repository;

import com.starterkit.springboot.brs.models.DeliverableByTecker;
import com.starterkit.springboot.brs.models.TeckerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeckerRepository extends JpaRepository<TeckerAssignment, UUID> {

    @Query("SELECT new com.starterkit.springboot.brs.models.DeliverableByTecker(ta.id, d.title, d.dueDate, ta.status) FROM TeckerAssignment ta INNER JOIN Deliverable d ON ta.deliverableId = d.id WHERE ta.teckerId = :teckerId")
    List<DeliverableByTecker> deliverableByTecker(UUID teckerId);

    @Query(value = "SELECT DISTINCT true FROM teckers_by_parents WHERE tecker_id = :teckerId AND parent_id = :parentId", nativeQuery = true)
    Optional<Boolean> areFamily(UUID teckerId, UUID parentId);

}
