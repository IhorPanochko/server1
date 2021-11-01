package com.example.server1.services;

import com.example.server1.documents.Message;
import com.example.server1.dto.MessageDto;
import com.example.server1.kafka.ProducerDTO;
import com.example.server1.repository.MessageRepository;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    private final ReactiveKafkaProducerTemplate<String, ProducerDTO> producer;

    private final MessageRepository messageRepository;

    public MessageServiceImpl(ReactiveKafkaProducerTemplate<String, ProducerDTO> producer, MessageRepository messageRepository) {
        this.producer = producer;
        this.messageRepository = messageRepository;
    }


    @Override
    public Mono<Void> sendMessage(MessageDto messageDto) {
        producer.send("topic1", new ProducerDTO("sdsdsd")).subscribe();
        return Mono.empty();
    }

    @Override
    public Mono<Void> create(Message message) {
        messageRepository.save(message).subscribe();
        return Mono.empty();
    }
}
