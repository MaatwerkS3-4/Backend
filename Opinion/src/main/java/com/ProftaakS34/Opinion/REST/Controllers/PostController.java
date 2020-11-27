package com.ProftaakS34.Opinion.REST.Controllers;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.REST.Models.PostInfo;
import com.ProftaakS34.Opinion.REST.Services.CommentService;
import com.ProftaakS34.Opinion.REST.Services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(PostController.BASE_URL)
@CrossOrigin(origins = {"http://localhost:3000"})
public class PostController {

    public static final String BASE_URL = "api/post";

    private final PostService postService;
    private final CommentService commentService;


    public PostController(PostService postService, CommentService commentService)
    {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<PostInfo> getAllPosts(){
        List<PostInfo> returnList = new ArrayList<>();
        for (Post post : postService.findAllPosts()) {
            returnList.add(turnPostIntoPostInfo(post));
        }
        return returnList;
    }

    @GetMapping("/id/{id}")
    public PostInfo getPostById(@PathVariable Long id){
        return turnPostIntoPostInfo(postService.findPostById(id));
    }

    @GetMapping("/subject/{subject}")
    public PostInfo getPostBySubject(@PathVariable String subject){
        return turnPostIntoPostInfo(postService.findPostBySubject(subject));
    }

    @GetMapping("/partialString/{string}")
    public List<PostInfo> getPostsByPartialSubject(@PathVariable String string){
         List<Post> posts = postService.findPostsByPartialSubject(string);
         List<PostInfo> returnList = new ArrayList<>();
        for (Post post : posts) {
            returnList.add(turnPostIntoPostInfo(post));
        }
        return returnList;
    }

    private PostInfo turnPostIntoPostInfo(Post post) {
        return new PostInfo(post.getId(),post.getSubject(),post.getUser().getUsername(),commentService.findCommentsByPostId(post.getId()).size(), commentService.getAmountOfParticipantsByPostId(post.getId()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post savePost(@RequestBody Post post){
        System.out.print(post);
        return postService.savePost(post);
    }
}
