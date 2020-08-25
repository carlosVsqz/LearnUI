package com.starterkit.springboot.brs.controller;

import com.starterkit.springboot.brs.Enum.RelationType;
import com.starterkit.springboot.brs.config.auth.TokenInfoService;
import com.starterkit.springboot.brs.dto.DeliverableDTO;
import com.starterkit.springboot.brs.dto.DeliverableResourcesDTO;
import com.starterkit.springboot.brs.dto.DeliverableToTeckerDTO;
import com.starterkit.springboot.brs.models.Resource;
import com.starterkit.springboot.brs.services.DeliverableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/deliverable")
public class DeliverableController {

    private DeliverableService deliverableService;

    private TokenInfoService tokenInfoService;

    @Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<DeliverableDTO> save(@RequestBody DeliverableDTO deliverableDTO) {
        return new ResponseEntity<>(deliverableService.save(deliverableDTO), HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping("{deliverableId}/teckers")
    public void assignToDeliverable(@PathVariable UUID deliverableId, @RequestBody DeliverableToTeckerDTO teckers) {
        deliverableService.assignToDeliverable(deliverableId, teckers.getTeckersToAssign());
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<DeliverableDTO>> getDeliverablesByBatch(@PathVariable UUID batchId) {
        return new ResponseEntity<>(deliverableService.findByBatch(batchId), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @PutMapping("/{deliverableId}")
    public ResponseEntity<DeliverableDTO> update(@RequestBody DeliverableDTO deliverableDTO, @PathVariable UUID deliverableId) {
        return new ResponseEntity<>(deliverableService.update(deliverableDTO, deliverableId), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @DeleteMapping("/{deliverableId}")
    public void deleteDeliverable(@PathVariable UUID deliverableId) {
        deliverableService.delete(deliverableId);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping("/{deliverableId}/{sessionId}/{type}")
    public void assignDeliverableToSession(@PathVariable UUID deliverableId, @PathVariable UUID sessionId, @PathVariable RelationType type) {
        deliverableService.assignDeliverableToSession(deliverableId, sessionId, type);
    }

    @GetMapping("/{deliverableId}")
    public ResponseEntity<DeliverableResourcesDTO> getDeliverable(@PathVariable UUID deliverableId) {
        return new ResponseEntity<>(deliverableService.getDeliverable(deliverableId), HttpStatus.OK);
    }


    @PutMapping("/{deliverableId}/resources")
    public void addResourcesToDeliverable(@PathVariable UUID deliverableId, @RequestBody List<Resource> resources) {
        deliverableService.addResourcesToDeliverable(deliverableId, resources);
    }

    @GetMapping("/{deliverableId}/resources")
    public ResponseEntity<List<Resource>> getResourcesByDeliverable(@PathVariable UUID deliverableId) {
        return new ResponseEntity(deliverableService.getResourcesByDeliverable(deliverableId), HttpStatus.OK);
    }

    @DeleteMapping("/{deliverableId}/resources/{resourceId}")
    public void deleteResourceFromDeliverable(@PathVariable UUID deliverableId, @PathVariable UUID resourceId, Principal principal) {
        deliverableService.deleteResourceFromDeliverable(tokenInfoService.getIdFromPrincipal(principal), deliverableId, resourceId);
    }

    @Autowired
    public void setDeliverableService(DeliverableService deliverableService) {
        this.deliverableService = deliverableService;
    }

    @Autowired
    public void setTokenInfoService(TokenInfoService tokenInfoService) {
        this.tokenInfoService = tokenInfoService;
    }
}
