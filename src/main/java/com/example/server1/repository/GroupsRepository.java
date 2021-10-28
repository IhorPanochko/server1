package com.example.server1.repository;

import com.example.server1.documents.Groups;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends ReactiveMongoRepository<Groups, String> {
}
