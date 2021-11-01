package com.example.server1.controllers;

import com.example.server1.dto.MessageDto;
import com.example.server1.services.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public Mono<Void> callKafka(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto).subscribe();
        return Mono.empty();
    }
}
