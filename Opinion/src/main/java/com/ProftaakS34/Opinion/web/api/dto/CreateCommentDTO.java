package com.ProftaakS34.Opinion.web.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentDTO {
    private String content;
    private long posterId;
}
