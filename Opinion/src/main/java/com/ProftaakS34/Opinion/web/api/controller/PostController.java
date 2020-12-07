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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
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

    @GetMapping
    private ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> resource = new ArrayList<>();
        for(Post p : postService.findAllPosts()){
            resource.add(postMapper.toDTO(p));
        }

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{postId}")
    private ResponseEntity<PostDTO> getPostById(@PathVariable long postId){
        PostDTO resource = postMapper.toDTO(postService.findPostById(postId));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    private ResponseEntity<PostDTO> savePost(@RequestBody CreatePostDTO dto){
        Post post = postService.savePost(dto.getUserId(), dto.getSubject());
        PostDTO resource = postMapper.toDTO(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
