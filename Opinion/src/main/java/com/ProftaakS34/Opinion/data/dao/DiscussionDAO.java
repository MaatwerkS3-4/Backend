package com.ProftaakS34.Opinion.data.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @Column(name = "time_stamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "UTC")
    private Date timeStamp;

    @ManyToOne
    private UserDAO poster;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CommentDAO> comments = new ArrayList<>();

    @ElementCollection
    public List<String> tags = new ArrayList<>();
}
