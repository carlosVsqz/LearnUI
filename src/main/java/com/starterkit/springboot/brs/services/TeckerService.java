package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.dto.DeliverableByTeckerDTO;

import java.util.List;
import java.util.UUID;

public interface TeckerService {

    List<DeliverableByTeckerDTO> getDeliverableByTecker(UUID teckerId);

    List<DeliverableByTeckerDTO> getDeliverableByKid(UUID teckerId, UUID parentId);


}
