package com.ProftaakS34.Opinion.Data.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    private long userId;

    public Comment(String content){
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return content;
    }
}
