package com.ProftaakS34.Opinion.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDiscussionDTO {
    private long userId;
    private String subject;
    private String description;
}
