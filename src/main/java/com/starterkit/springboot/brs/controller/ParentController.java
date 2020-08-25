package com.starterkit.springboot.brs.controller;

import com.starterkit.springboot.brs.config.auth.TokenInfoService;
import com.starterkit.springboot.brs.dto.TeckerDTO;
import com.starterkit.springboot.brs.dto.TeckersToParentDTO;
import com.starterkit.springboot.brs.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parent")
public class ParentController {

    private ParentService parentService;
    private TokenInfoService tokenInfoService;


    @PutMapping("/{parentId}")
    public ResponseEntity<HttpStatus> assignTeckersToParent(@PathVariable UUID parentId, @RequestBody TeckersToParentDTO teckers) {
        parentService.assignToParent(parentId, teckers.getTeckersId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<List<TeckerDTO>> getTeckersByParent(@PathVariable UUID parentId) {

        return new ResponseEntity<>(parentService.getTeckersByParent(parentId), HttpStatus.OK);
    }

    @Secured({"ROLE_PARENT"})
    @GetMapping("/teckers")
    public ResponseEntity<List<TeckerDTO>> getTeckers(Principal principal) {

        return new ResponseEntity<>(parentService.getTeckersByParentLogged(tokenInfoService.getIdFromPrincipal(principal)), HttpStatus.OK);

    }

    @Autowired
    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    @Autowired
    public void setTokenInfoService(TokenInfoService tokenInfoService) {
        this.tokenInfoService = tokenInfoService;
    }

}
