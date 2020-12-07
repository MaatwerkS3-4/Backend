package com.ProftaakS34.Opinion.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private long id;
    private String content;
    private User poster;

    public Comment(String content, User poster) {
        this.content = content;
        this.poster = poster;
    }
}
