package com.example.server1.controllers;

import com.example.server1.dto.GroupDto;
import com.example.server1.services.BaseClient;
import com.example.server1.services.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("addGroups")
    public void addUsers() {
        groupService.addGroups();
    }


    @GetMapping("/group/{id}")
    public Mono<GroupDto> getGroup(@PathVariable String id) {
        return groupService.getById(id);
    }

    @GetMapping("/calKafka")
    public void callKafka() {
        groupService.callKafka();
    }

}
