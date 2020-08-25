package com.starterkit.springboot.brs.repository;

import java.util.List;
import java.util.UUID;

import com.starterkit.springboot.brs.dto.ResourceDTO;
import com.starterkit.springboot.brs.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    @Query("SELECT r FROM Resource r JOIN MessagesResources mr ON r.id = mr.resourceId WHERE mr.messageId = :messageId")
    List<Resource> getResourcesBySpecificMessage(UUID messageId);

    @Modifying
    @Query(value = "DELETE FROM resources WHERE id = :resourceId", nativeQuery = true)
    void deleteResource(UUID resourceId);

    @Query("SELECT new com.starterkit.springboot.brs.dto.ResourceDTO(r.url, r.mimeType) FROM Resource r JOIN DeliverableResources dr ON r.id = dr.resourceId WHERE dr.deliverableId = :deliverableId ")
    List<ResourceDTO> getResourcesByDeliverable(UUID deliverableId);

}
