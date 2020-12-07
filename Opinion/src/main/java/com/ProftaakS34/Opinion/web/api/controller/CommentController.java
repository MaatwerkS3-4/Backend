package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.PostMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.CreateCommentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(tags = "Comments")
public class CommentController {
    private final PostService postService;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentController(PostService postService, CommentMapper commentMapper) {
        this.postService = postService;
        this.commentMapper = commentMapper;
    }

    /**
     * Posts a comment on a discussion
     * @param postId The id of the discussion
     * @param dto   The {@link CreateCommentDTO} which contains the data for the comment
     * @return  The {@link ResponseEntity} with status {@code 201 (CREATED)} and with the body {@link CommentDTO}
     */
    @ApiOperation(
            value = "Post a comment on a discussion"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Created - comment has been posted")
    })
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> saveComment(
            @PathVariable long postId, @RequestBody CreateCommentDTO dto){
        Comment comment = postService.postComment(postId, dto.getPosterId(), dto.getContent());
        CommentDTO resource = commentMapper.toDTO(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
