package com.ProftaakS34.Opinion.REST.Controllers;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import com.ProftaakS34.Opinion.REST.Services.CommentService;
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
    List<Comment> getAllComments(){
        return commentService.findAllComments();
    }

    @GetMapping("/id/{id}")
    public Comment getPostById(@PathVariable Long id){
        return commentService.findCommentById(id);
    }

    @GetMapping("/postId/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId){
        return commentService.findCommentsByPostId(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment savePost(@RequestBody Comment comment){
        return commentService.saveComment(comment);
    }
}
