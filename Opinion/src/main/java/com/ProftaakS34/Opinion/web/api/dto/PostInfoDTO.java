package com.ProftaakS34.Opinion.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostInfoDTO {
    private long postId;
    private String postSubject;
    private UserDTO poster;
    private int numberOfParticipants;
    private int numberOfComments;
}
