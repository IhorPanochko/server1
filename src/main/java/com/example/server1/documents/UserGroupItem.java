package com.example.server1.documents;

import javax.persistence.Embeddable;

@Embeddable
public class UserGroupItem {

    private String id;

    private Long zip;

    public UserGroupItem() {
    }

    public UserGroupItem(String id, Long zip) {
        this.id = id;
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getZip() {
        return zip;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }
}
