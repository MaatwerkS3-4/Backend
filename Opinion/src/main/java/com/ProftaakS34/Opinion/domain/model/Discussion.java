package com.ProftaakS34.Opinion.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public Discussion(String subject, String description, User poster) {
        this.subject = subject;
        this.description = description;
        this.timeStamp = new Date();
        this.poster = poster;
    }
}
