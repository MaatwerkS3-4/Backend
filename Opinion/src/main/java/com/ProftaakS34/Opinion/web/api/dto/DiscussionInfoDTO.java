package com.ProftaakS34.Opinion.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
}
