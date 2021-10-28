package com.example.server1.dto;

import java.util.List;

public class GroupDto {

    private String id;

    private String name;

    private List<UserGroupDto> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGroupDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserGroupDto> users) {
        this.users = users;
    }
}
