package com.ProftaakS34.Opinion.Data.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

    @Id @GeneratedValue
    private long id;
    private String subject;
    private User user;

    public Post(String subject, User user){
        this.subject = subject;
        this.user = user;
    }

    public Post(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wat vindt je van " + subject + "?";
    }
}
