package com.ProftaakS34.Opinion.web.api.dto;

import com.ProftaakS34.Opinion.domain.model.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateDiscussionDTO {
    private long userId;
    private String subject;
    private String description;
    @ApiModelProperty(
            value = "A maximum of 3 tags"
    )
    private List<Category> tags;
}
