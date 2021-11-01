package com.example.server1.repository;

import com.example.server1.documents.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {


}
