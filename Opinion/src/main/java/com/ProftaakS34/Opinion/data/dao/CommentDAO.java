package com.ProftaakS34.Opinion.data.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "time_stamp")
    @JsonFormat(pattern = "yyyy-MM-ddThh:mm")
    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm")
    private Date timeStamp;

    @ManyToOne
    private UserDAO poster;
}
