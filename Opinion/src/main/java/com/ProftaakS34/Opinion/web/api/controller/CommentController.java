package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.web.api.dto.CreateCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommentController.BASE_URL)
public class CommentController {

    public static final String BASE_URL = "api/comment";
//
//    private final CommentService commentService;
//    private final PostService postService;
//
//    @Autowired
//    public CommentController(CommentService commentService, PostService postService) {
//        this.commentService = commentService;
//        this.postService = postService;
//    }
//
//
//    //    @GetMapping
////    public List<Comment> getAllComments(){
////
////        return commentService.findAllComments();
////    }
//
////    @GetMapping("/id/{id}")
////    public CommentDAO getCommentById(@PathVariable Long id){
////        return commentService.findCommentById(id);
////    }
//
//    @GetMapping("/postId/{postId}")
//    public List<Comment> getCommentsByPostId(@PathVariable Long postId){
//        Post post = postService.findPostById(postId);
//        return post.getComments();
//    }
//
//    @PostMapping("/{postId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Comment saveComment(@PathVariable Long postId, @RequestBody CreateCommentDTO dto){
//        return postService.postComment(postId, dto.getUserId(), dto.getContent());
//    }

}
