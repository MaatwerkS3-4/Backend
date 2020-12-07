package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.domain.mapper.PostMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.web.api.dto.PostDTO;
import com.ProftaakS34.Opinion.web.api.dto.PostInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post_info")
@Api(tags = "PostInfo")
public class PostInfoController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @Autowired
    public PostInfoController(PostService postService, CommentService commentService, UserMapper userMapper, PostMapper postMapper) {
        this.postService = postService;
        this.commentService = commentService;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
    }

    /**
     * Retrieves info about all existing posts and returns it as a {@link List<PostInfoDTO>}
     *
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the info about found discussions {@link List<PostInfoDTO>}
     */
    @ApiOperation(
            value = "Get info about all existing discussions"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - the list info about existing discussions has been returned")
    })
    @GetMapping
    private ResponseEntity<List<PostInfoDTO>> getAllPostInfo(){
        List<PostInfoDTO> resource = new ArrayList<>();
        for(Post p : postService.findAllPosts()){
            resource.add(toDTO(p));
        }

        return ResponseEntity.ok(resource);
    }

    /**
     * Retrieves info about a discussion by it's id and returns it as a {@link PostInfoDTO}
     *
     * @param postId The id of the discussion for which to retrieve info
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the found information {@link PostInfoDTO}
     */
    @ApiOperation(
            value = "Get info about a specific discussion by it's id"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Ok - the info about the specific discussion has been returned")
    })
    @GetMapping("/id/{postId}")
    public ResponseEntity<PostInfoDTO> getPostInfoByID(@PathVariable long postId){
        Post post = postService.findPostById(postId);
        PostInfoDTO resource = toDTO(post);
        return ResponseEntity.ok(resource);
    }

    /**
     * Retrieves info about a discussion by it's subject and returns it as a {@link PostInfoDTO}
     *
     * @param subject The subject of the discussion for which to retrieve info
     * @return The {@link ResponseEntity} with status {@code 200 (ok)} with as body the found information {@link PostInfoDTO}
     */
    @ApiOperation(
            value = "Get info about a specific discussion by it's subject"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Ok - the info about the specific discussion has been returned")
    })
    @GetMapping("/subject/{subject}")
    public ResponseEntity<PostInfoDTO> getPostInfoBySubject(@PathVariable String subject){
        Post post = postService.findPostBySubject(subject);
        PostInfoDTO resource = toDTO(post);
        return ResponseEntity.ok(resource);
    }

    private PostInfoDTO toDTO(Post model){

        int parCount = commentService.getAmountOfParticipantsByPostId(model);
        int comCount = model.getComments().size();

        return new PostInfoDTO(
                model.getId(),
                model.getSubject(),
                userMapper.toDTO(model.getPoster()),
                parCount,
                comCount);
    }
}
