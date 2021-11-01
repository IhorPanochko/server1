package com.example.server1.services;

import com.example.server1.documents.Message;
import com.example.server1.dto.MessageDto;
import reactor.core.publisher.Mono;

public interface MessageService {

    Mono<Void> sendMessage(MessageDto messageDto);

    Mono<Void> create(Message message);
}
