package com.ProftaakS34.Opinion.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Discussion {
    private long id;
    private String subject;
    private String description;
    private Date timeStamp;
    private User poster;
    private List<Comment> comments = new ArrayList<>();
    private List<User> upvoters = new ArrayList<>();
    private List<Category> tags = new ArrayList<>();

    public Discussion(String subject, String description, User poster, List<Category> tags) {
        this.subject = subject;
        this.description = description;
        this.timeStamp = new Date();
        this.poster = poster;
        this.tags = tags;
    }
}
