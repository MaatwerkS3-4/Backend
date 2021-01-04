package com.ProftaakS34.Opinion.web.api.dto;

import com.ProftaakS34.Opinion.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DiscussionInfoDTO {
    private long id;
    private String subject;
    private UserDTO poster;
    private int numberOfParticipants;
    private int numberOfComments;
    private Date timeStamp;
    private List<Category> tags;
}
