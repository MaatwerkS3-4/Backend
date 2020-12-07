package com.ProftaakS34.Opinion.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class CommentDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private UserDAO poster;

    public CommentDAO(String content, UserDAO poster) {
        this.content = content;
        this.poster = poster;
    }
}
