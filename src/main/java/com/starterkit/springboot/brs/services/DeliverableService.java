package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.Enum.RelationType;
import com.starterkit.springboot.brs.dto.DeliverableDTO;
import com.starterkit.springboot.brs.dto.DeliverableResourcesDTO;
import com.starterkit.springboot.brs.models.Deliverable;
import com.starterkit.springboot.brs.models.Resource;

import java.util.UUID;

import java.util.List;
import java.util.Optional;


public interface DeliverableService {

    Optional<Deliverable> findById(UUID id);

    DeliverableDTO save(DeliverableDTO deliverableDTO);

    void removeAssignmentFromThisDeliverableToTecker(UUID deliverableId);

    void assignToDeliverable(UUID deliverableId, UUID tekerId);

    void assignToDeliverable(UUID deliverableId, List<UUID> teckersToAssign);

    void assignTeckerToDeliverable(UUID deliverableId, UUID batchId, List<UUID> teckersToAssign);

    List<DeliverableDTO> findByBatch(UUID batchId);

    DeliverableDTO update(DeliverableDTO deliverableDTO, UUID deliverableId);

    void delete(UUID deliverableId);

    void assignDeliverableToSession(UUID deliverableId, UUID sessionId, RelationType type);

    DeliverableResourcesDTO getDeliverable(UUID deliverableId);

    void addResourcesToDeliverable(UUID deliverableIds, List<Resource> resource);

    List<Resource> getResourcesByDeliverable(UUID deliverableId);

    void deleteResourceFromDeliverable(UUID teckerId, UUID deliverableId, UUID resourceId);

}
