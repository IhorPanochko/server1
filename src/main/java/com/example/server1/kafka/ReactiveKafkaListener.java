package com.example.server1.kafka;

import com.example.server1.documents.Message;
import com.example.server1.services.MessageService;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ReactiveKafkaListener {
    //
//    private final ReactiveKafkaConsumerTemplate<String, ProducerDTO> consumer;
//
//    public ReactiveKafkaListener(ReactiveKafkaConsumerTemplate<String, ProducerDTO> consumer) {
//        this.consumer = consumer;
//    }


    private final MessageService messageService;

    public ReactiveKafkaListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "topic2", groupId = "reactive", clientIdPrefix = "reactive")
    public void listen(@Payload ProducerDTO producerDTO) {
        ProducerDTO result = getProducerDTO(producerDTO);
        System.out.println(result);
        Message message = toEntity(result);
        messageService.create(message).subscribe();
    }

    private ProducerDTO getProducerDTO(ProducerDTO producerDTO) {
        String body = producerDTO.getMessage();
        Gson g = new Gson();
        return g.fromJson(body, ProducerDTO.class);
    }

    private Message toEntity(ProducerDTO producerDTO) {
        return new Message(producerDTO.getMessage());
    }
}
