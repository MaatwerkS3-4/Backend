package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.CreateCommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.DiscussionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Api(tags = "Comments")
public class CommentController {
    private final DiscussionService discussionService;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentController(DiscussionService discussionService, CommentMapper commentMapper) {
        this.discussionService = discussionService;
        this.commentMapper = commentMapper;
    }

    /**
     * Posts a comment on a discussion
     * @param discussionId The id of the discussion
     * @param dto   The {@link CreateCommentDTO} which contains the data for the comment
     * @return  The {@link ResponseEntity} with status {@code 201 (CREATED)} and with the body {@link CommentDTO}
     */
    @ApiOperation(
            value = "Post a comment on a discussion"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Created - comment has been posted")
    })
    @PostMapping("/{discussionId}")
    public ResponseEntity<CommentDTO> saveComment(
            @PathVariable long discussionId, @RequestBody CreateCommentDTO dto){
        Comment comment = discussionService.postComment(discussionId, dto.getPosterId(), dto.getContent());
        CommentDTO resource = commentMapper.toDTO(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    /**
     * Retrieves comments from a discussion by it's id and returns it as a {@link List<CommentDTO>}
     *
     *
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the info about found discussions {@link List<  DiscussionDTO  >}
     */

    /**
     * Retrieves comments from a discussion by it's id and returns it as a {@link List<CommentDTO>}
     *
     * @param discussionId The id of the discussion from which to retrieve the comments
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the comments from the discussion {@link List<CommentDTO>}
     */
    @ApiOperation(
            value = "Get all comments posted on a discussion"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - the list of comments has been returned")
    })
    @GetMapping("/{discussionId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByDiscussionId(@PathVariable long discussionId){
        List<Comment> comments = discussionService.getCommentsByDiscussionId(discussionId);
        List<CommentDTO> resource = new ArrayList<>();
        for(Comment c : comments){
            resource.add(commentMapper.toDTO(c));
        }

        return ResponseEntity.ok(resource);
    }
}
