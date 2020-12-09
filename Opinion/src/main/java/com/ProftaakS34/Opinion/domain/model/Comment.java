package com.ProftaakS34.Opinion.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private long id;
    private String content;
    private User poster;
    private Date timeStamp;

    public Comment(String content, User poster) {
        this.content = content;
        this.poster = poster;
        this.timeStamp = new Date();
    }
}
