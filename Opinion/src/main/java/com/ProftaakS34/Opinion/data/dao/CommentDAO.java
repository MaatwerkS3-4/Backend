package com.ProftaakS34.Opinion.data.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CommentDAO {
    @Id
    @GeneratedValue
    private long id;
    private String content;
    @ManyToOne
    private UserDAO userDAO;
    @ManyToOne
    private PostDAO postDAO;

    public CommentDAO(String content, UserDAO userDAO, PostDAO postDAO){
        this.content = content;
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    public CommentDAO(){}

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

    public UserDAO getUser() {
        return userDAO;
    }

    public void setUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public PostDAO getPost() {
        return postDAO;
    }

    public void setPost(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    public String toString() {
        return content;
    }
}
