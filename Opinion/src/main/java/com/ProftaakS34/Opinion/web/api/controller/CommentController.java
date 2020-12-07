package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommentController.BASE_URL)
public class CommentController {

    public static final String BASE_URL = "api/comment";

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDAO> getAllComments(){
        return commentService.findAllComments();
    }

    @GetMapping("/id/{id}")
    public CommentDAO getCommentById(@PathVariable Long id){
        return commentService.findCommentById(id);
    }

    @GetMapping("/postId/{postId}")
    public List<CommentDAO> getCommentsByPostId(@PathVariable Long postId){
        return commentService.findCommentsByPostId(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDAO saveComment(@RequestBody CommentDAO commentDAO){
        return commentService.saveComment(commentDAO);
    }

}
