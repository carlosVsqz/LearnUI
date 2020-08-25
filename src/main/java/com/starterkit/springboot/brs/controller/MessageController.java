package com.starterkit.springboot.brs.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.starterkit.springboot.brs.config.auth.TokenInfoService;
import com.starterkit.springboot.brs.dto.MessageBodyDTO;
import com.starterkit.springboot.brs.dto.MessageHeaderDTO;
import com.starterkit.springboot.brs.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
public class MessageController {

    private MessageService messageService;

    private TokenInfoService tokenInfoService;

    @GetMapping
    public ResponseEntity<Map<String, List<MessageHeaderDTO>>> retrieveMessage(Principal principal) {
        Map<String, List<MessageHeaderDTO>> messages = messageService.getMessagesByUser(tokenInfoService.getIdFromPrincipal(principal));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PutMapping("/{messageId}/read")
    public void markMessageAsRead(@PathVariable UUID messageId, Principal principal) {
        messageService.markMessageAsRead(messageId, tokenInfoService.getIdFromPrincipal(principal));
    }

    @PutMapping("/{messageId}/unread")
    public void markMessageAsUnread(@PathVariable UUID messageId, Principal principal) {
        messageService.markMessageAsUnread(messageId, tokenInfoService.getIdFromPrincipal(principal));
    }

    @PutMapping("/{messageId}/highPriority")
    public void markMessageAsHighPriority(@PathVariable UUID messageId, Principal principal) {
        messageService.markMessageAsHighPriority(messageId, tokenInfoService.getIdFromPrincipal(principal));
    }

    @PutMapping("/{messageId}/lowPriority")
    public void markMessageAsLowPriority(@PathVariable UUID messageId, Principal principal) {
        messageService.markMessageAsLowPriority(messageId, tokenInfoService.getIdFromPrincipal(principal));
    }

    @PutMapping("/{messageId}/received")
    public void confirmMessageReceived(@PathVariable UUID messageId, Principal principal) {
        messageService.confirmMessageReceived(messageId, tokenInfoService.getIdFromPrincipal(principal));
    }

    @PutMapping("/{messageId}/reading")
    public void confirmMessageReading(@PathVariable UUID messageId, Principal principal) {
        messageService.confirmMessageReading(messageId, tokenInfoService.getIdFromPrincipal(principal));
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageBodyDTO> getSpecificMessage(@PathVariable UUID messageId, Principal principal) {

        return new ResponseEntity<>(messageService.getSpecificMessageByUser(messageId, tokenInfoService.getIdFromPrincipal(principal)), HttpStatus.OK);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setTokenInfoService(TokenInfoService tokenInfoService) {
        this.tokenInfoService = tokenInfoService;
    }
}
