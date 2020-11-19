package com.ProftaakS34.Opinion.REST.Controllers;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.REST.Services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PostController.BASE_URL)
@CrossOrigin(origins = {"http://localhost:3000"})
public class PostController {

    public static final String BASE_URL = "api/post";

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.findAllPosts();
    }

    @GetMapping("/id/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }

    @GetMapping("/subject/{subject}")
    public Post getPostBySubject(@PathVariable String subject){
        return postService.findPostBySubject(subject);
    }

    @GetMapping("/partialString/{string}")
    public List<Post> getPostsByPartialSubject(@PathVariable String string){
        return postService.findPostsByPartialSubject(string);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post savePost(@RequestBody Post post){
        System.out.print(post);
        return postService.savePost(post);
    }
}
