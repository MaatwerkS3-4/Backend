package com.ProftaakS34.Opinion.web.api.dto;

import com.ProftaakS34.Opinion.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDTO {
    private long id;
    private String subject;
    private String description;
    private List<CommentDTO> comments = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private Category category;
}
