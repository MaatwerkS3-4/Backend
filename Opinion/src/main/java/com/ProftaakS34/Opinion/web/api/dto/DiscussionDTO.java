package com.ProftaakS34.Opinion.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionDTO {
    private long id;
    private String subject;
    private UserDTO poster;
    private int numberOfParticipants;
    private int numberOfComments;
}
