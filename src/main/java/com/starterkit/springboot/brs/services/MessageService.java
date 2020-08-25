package com.starterkit.springboot.brs.services;

import com.starterkit.springboot.brs.dto.MessageBodyDTO;
import com.starterkit.springboot.brs.dto.MessageHeaderDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface MessageService {

    Map<String, List<MessageHeaderDTO>> getMessagesByUser(UUID userReceiverId);

    void markMessageAsRead(UUID messageId, UUID userReceiverId);

    void markMessageAsUnread(UUID messageId, UUID userReceiverId);

    MessageBodyDTO getSpecificMessageByUser(UUID messageId, UUID userReceiverId);

    void markMessageAsHighPriority(UUID messageId, UUID userReceiverId);

    void markMessageAsLowPriority(UUID messageId, UUID userReceiverId);

    void confirmMessageReceived(UUID messageId, UUID userReceiverId);

    void confirmMessageReading(UUID messageId, UUID userReceiverId);
}
