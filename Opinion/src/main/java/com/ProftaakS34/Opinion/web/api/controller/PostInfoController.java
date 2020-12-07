package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.domain.mapper.PostMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.web.api.dto.PostDTO;
import com.ProftaakS34.Opinion.web.api.dto.PostInfoDTO;
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

    @GetMapping
    private ResponseEntity<List<PostInfoDTO>> getAllPostInfo(){
        List<PostInfoDTO> resource = new ArrayList<>();
        for(Post p : postService.findAllPosts()){
            resource.add(toDTO(p));
        }

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<PostDTO> getPostInfoByID(@PathVariable long postId){
        PostDTO resource = postMapper.toDTO(postService.findPostById(postId));
        return ResponseEntity.ok(resource);
    }

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
