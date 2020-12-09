package com.ProftaakS34.Opinion.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "discussion")
public class DiscussionDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private UserDAO poster;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CommentDAO> comments = new ArrayList<>();

    public DiscussionDAO(String subject, String description, UserDAO poster) {
        this.subject = subject;
        this.description = description;
        this.poster = poster;
    }
}
