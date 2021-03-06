package com.ProftaakS34.Opinion.web.api.dto;

import com.ProftaakS34.Opinion.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DiscussionInfoDTO {
    private long id;
    private String subject;
    private int numberOfParticipants;
    private int numberOfComments;
    private Date timeStamp;
    private boolean upvotedByUser;
    private int score;
    private List<Category> tags;
}
