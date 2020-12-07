package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PostService;
import com.ProftaakS34.Opinion.web.api.dto.PostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(PostController.BASE_URL)
@CrossOrigin(origins = {"http://localhost:3000"})
public class PostController {

    public static final String BASE_URL = "api/post";
//
//    private final PostService postService;
//    private final CommentService commentService;
//
//
//    public PostController(PostService postService, CommentService commentService)
//    {
//        this.postService = postService;
//        this.commentService = commentService;
//    }
//
//    @GetMapping
//    public List<Post> getAllPosts(){
//        List<PostDTO> returnList = new ArrayList<>();
//        for (PostDAO postDAO : postService.findAllPosts()) {
//            returnList.add(turnPostIntoPostInfo(postDAO));
//        }
//        return returnList;
//    }
//
//    @GetMapping("/id/{id}")
//    public Post getPostById(@PathVariable Long id){
//        return turnPostIntoPostInfo(postService.findPostById(id));
//    }
//
//    @GetMapping("/subject/{subject}")
//    public Post getPostBySubject(@PathVariable String subject){
//        return turnPostIntoPostInfo(postService.findPostBySubject(subject));
//    }
//
//    @GetMapping("/partialString/{string}")
//    public List<Post> getPostsByPartialSubject(@PathVariable String string){
//         List<PostDAO> postDAOS = postService.findPostsByPartialSubject(string);
//         List<PostDTO> returnList = new ArrayList<>();
//        for (PostDAO postDAO : postDAOS) {
//            returnList.add(turnPostIntoPostInfo(postDAO));
//        }
//        return returnList;
//    }
//
//    private Post turnPostIntoPostInfo(PostDAO postDAO) {
//        return new PostDTO(postDAO.getId(), postDAO.getSubject(), postDAO.getUser().getUsername(),commentService.findCommentsByPostId(postDAO.getId()).size(), commentService.getAmountOfParticipantsByPostId(postDAO.getId()));
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PostDAO savePost(@RequestBody PostDAO postDAO){
//        System.out.print(postDAO);
//        return postService.savePost(postDAO);
//    }
}
