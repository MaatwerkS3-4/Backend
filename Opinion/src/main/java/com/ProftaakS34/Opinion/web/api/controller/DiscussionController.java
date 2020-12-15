package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.web.api.dto.CreateDiscussionDTO;
import com.ProftaakS34.Opinion.web.api.dto.DiscussionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discussion")
@Api(tags = "Discussions")
public class DiscussionController {

    private final DiscussionService discussionService;
    private final DiscussionMapper discussionMapper;
    private final CommentService commentService;
    private final UserMapper userMapper;

    @Autowired
    public DiscussionController(DiscussionService discussionService, DiscussionMapper discussionMapper, CommentService commentService, UserMapper userMapper) {
        this.discussionService = discussionService;
        this.discussionMapper = discussionMapper;
        this.commentService = commentService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves a discussion by it's id and returns it as a {@link DiscussionDTO}
     *
     * @param discussionId The id of the discussion to be found
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the found discussion {@link DiscussionDTO}
     * todo: track exceptions with @throws
     */
    @ApiOperation(
            value = "Get a discussion by it's id"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - The discussion with the given id has been returned.")
            //todo: implement exceptions
    })
    @GetMapping("/{discussionId}")
    private ResponseEntity<DiscussionDTO> getDiscussionById(@PathVariable long discussionId){
        DiscussionDTO resource = discussionMapper.toDTO(discussionService.findDiscussionById(discussionId));
        return ResponseEntity.ok(resource);
    }

    /**
     * Create a new discussion
     *
     * @param dto The {@link CreateDiscussionDTO} which contains the information to create the discussion
     * @return The {@link ResponseEntity} with status {@code 201 (CREATED)} with as body the created discussion {@link DiscussionDTO}
     */
    @ApiOperation(
            value = "Create a new discussion"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - discussion has been created")
    })
    @PostMapping
    private ResponseEntity<DiscussionDTO> saveDiscussion(@RequestBody CreateDiscussionDTO dto){
        Discussion discussion = discussionService.postDiscussion(dto.getUserId(), dto.getSubject(), dto.getDescription(), dto.getTags());
        DiscussionDTO resource = discussionMapper.toDTO(discussion);

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
