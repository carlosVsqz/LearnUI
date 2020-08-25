package com.starterkit.springboot.brs.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.starterkit.springboot.brs.models.Batch;
import com.starterkit.springboot.brs.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    List<Session> findByBatch(Batch batch);

    @Modifying
    @Query(value = "INSERT INTO confirm_attendance (session_id, user_id) VALUES (:sessionId, :userId)", nativeQuery = true)
    void confirmAttendance(UUID sessionId, UUID userId);

    @Query(value = "SELECT s FROM Session s JOIN UserByBatch ub ON s.batch.id = ub.batchId JOIN User u ON ub.userId = u.id WHERE u.id = :userId AND year(s.date) =:year AND month(s.date) = :month ")
    List<Session> getSessionsByUserByDate(int year, int month, UUID userId);

    @Query(value = "SELECT DISTINCT true FROM confirm_attendance WHERE session_id = :sessionId", nativeQuery = true)
    Optional<Boolean> doesSessionHaveUsersConfirmed(UUID sessionId);
}
