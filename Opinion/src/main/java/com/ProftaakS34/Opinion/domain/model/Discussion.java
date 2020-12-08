package com.ProftaakS34.Opinion.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Discussion {
    private long id;
    private String subject;
    private User poster;
    private List<Comment> comments = new ArrayList<>();
}
