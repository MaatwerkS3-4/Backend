package com.ProftaakS34.Opinion.data.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PostDAO {

    @Id @GeneratedValue
    private long id;
    private String subject;
    @ManyToOne
    private UserDAO userDAO;

    public PostDAO(String subject, UserDAO userDAO){
        this.subject = subject;
        this.userDAO = userDAO;
    }

    public PostDAO(){

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

    public UserDAO getUser() {
        return userDAO;
    }

    public void setUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String toString() {
        return "Wat vindt je van " + subject + "?";
    }
}
