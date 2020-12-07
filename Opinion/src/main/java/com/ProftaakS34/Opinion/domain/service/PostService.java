package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.repository.PostRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.PostMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;
    private final CommentService commentService;

    private final PostMapper postMapper;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, CommentService commentService, PostMapper postMapper, UserMapper userMapper, CommentMapper commentMapper) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.commentService = commentService;
        this.postMapper = postMapper;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    public Post findPostById(Long id) {
        return postMapper.toModel(postRepository.findById(id).get());
    }

    public Post findPostBySubject(String subject) {

        for(PostDAO postDAO : postRepository.findAll()){
            if(postDAO.getSubject().toLowerCase().equals(subject.toLowerCase())){
                return postMapper.toModel(postDAO);
            }
        }
        return null;
    }

    public List<Post> findPostsByPartialSubject(String partialSubject) {
        List<Post> matches = new ArrayList<>();
        for(PostDAO postDAO : postRepository.findAll()){
            if(postDAO.getSubject().contains(partialSubject)){
                matches.add(postMapper.toModel(postDAO));
            }
        }
        return matches;
    }

    public List<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        for(PostDAO p : postRepository.findAll()){
            posts.add(postMapper.toModel(p));
        }

        return posts;
    }

    public Post savePost(long userId, String subject) {
        if(subject == null || subject.isEmpty()) throw new IllegalArgumentException("subject is null or empty");
        User poster = userService.findUserById(userId);
        if(poster == null) throw new IllegalArgumentException("user is null or incorrect");

        PostDAO dao = postRepository.save(new PostDAO(subject, userMapper.toDAO(poster)));
        return postMapper.toModel(dao);
    }

    public Comment postComment(long postId, long posterId, String content) {

        //Find post
        Optional<PostDAO> postPromise = postRepository.findById(postId);
        if(postPromise.isEmpty()) throw new IllegalArgumentException("Post not found");
        PostDAO post = postPromise.get();

        //Create message
        Comment comment = commentService.saveComment(posterId, content);

        //Add comment to post
        post.getComments().add(commentMapper.toDAO(comment));
        postRepository.save(post);

        return comment;
    }
}
