package com.ProftaakS34.Opinion.web.api.dto;

public class PostDTO {
    private long id;
    private String subject;
    private String username;
    private int amountOfComments;
    private int amountOfParticipants;

    public PostDTO(long id, String subject, String username, int amountOfComments, int amountOfParticipants) {
        this.id = id;
        this.subject = subject;
        this.username = username;
        this.amountOfComments = amountOfComments;
        this.amountOfParticipants = amountOfParticipants;
    }

    public int getAmountOfParticipants() {
        return amountOfParticipants;
    }

    public void setAmountOfParticipants(int amountOfParticipants) {
        this.amountOfParticipants = amountOfParticipants;
    }

    public int getAmountOfComments() {
        return amountOfComments;
    }

    public void setAmountOfComments(int amountOfComments) {
        this.amountOfComments = amountOfComments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
