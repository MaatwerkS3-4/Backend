package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.domain.mapper.PostMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.web.api.dto.CreatePostDTO;
import com.ProftaakS34.Opinion.web.api.dto.PostDTO;
import com.ProftaakS34.Opinion.web.api.dto.PostInfoDTO;
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
@RequestMapping("/posts")
@Api(tags = "Posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentService commentService;
    private final UserMapper userMapper;

    @Autowired
    public PostController(PostService postService, PostMapper postMapper, CommentService commentService, UserMapper userMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentService = commentService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves a discussion by it's id and returns it as a {@link PostDTO}
     *
     * @param postId The id of the discussion to be found
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the found discussion {@link PostDTO}
     * todo: track exceptions with @throws
     */
    @ApiOperation(
            value = "Get a discussion by it's id"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - The discussion with the given id has been returned.")
            //todo: implement exceptions
    })
    @GetMapping("/{postId}")
    private ResponseEntity<PostDTO> getPostById(@PathVariable long postId){
        PostDTO resource = postMapper.toDTO(postService.findPostById(postId));
        return ResponseEntity.ok(resource);
    }

    /**
     * Create a new discussion
     *
     * @param dto The {@link CreatePostDTO} which contains the information to create the post
     * @return The {@link ResponseEntity} with status {@code 201 (CREATED)} with as body the created discussion {@link PostDTO}
     */
    @ApiOperation(
            value = "Create a new discussion"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - discussion has been created")
    })
    @PostMapping
    private ResponseEntity<PostDTO> savePost(@RequestBody CreatePostDTO dto){
        Post post = postService.savePost(dto.getUserId(), dto.getSubject());
        PostDTO resource = postMapper.toDTO(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
