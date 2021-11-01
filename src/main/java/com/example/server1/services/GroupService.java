package com.example.server1.services;

import com.example.server1.dto.GroupDto;
import reactor.core.publisher.Mono;

public interface GroupService {

    Mono<GroupDto> getById(String id);

    void addGroups();

    void callKafka();
}
