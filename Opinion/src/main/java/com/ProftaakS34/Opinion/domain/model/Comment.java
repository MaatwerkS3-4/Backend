package com.ProftaakS34.Opinion.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private long id;
    private String content;
    private User poster;
    private Date timeStamp;
    private List<Comment> replies = new ArrayList<>();
    private List<User> upvoters = new ArrayList<>();

    public Comment(String content, User poster) {
        this.content = content;
        this.poster = poster;
        this.timeStamp = new Date();
    }
}
