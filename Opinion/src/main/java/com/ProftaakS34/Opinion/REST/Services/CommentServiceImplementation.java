package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService{

    private final CommentRepository commentRepository;
    private final UserServiceImplementation userRepo;
    private final PostServiceImplementation postRepo;

    public CommentServiceImplementation(CommentRepository commentRepository, UserServiceImplementation userRepo, PostServiceImplementation postRepo) {
        this.commentRepository = commentRepository;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @Override
    public Comment findCommentById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            return commentRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        List<Comment> results = new ArrayList<>();
        for(Comment comment : commentRepository.findAll()){
            if(comment.getPost().getId() == postId){
                results.add(comment);
            }
        }
        return results;
    }

    @Override
    public List<Comment> findAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment saveComment(Comment comment) {
        if (comment.getContent() == null || comment.getContent().isEmpty()) throw new IllegalArgumentException("content is empty or null");
        if (comment.getPost() == null) throw new IllegalArgumentException("post is null");
        if (comment.getUser() == null) throw new IllegalArgumentException("user is null");
        if (userRepo.findUserById(comment.getUser().getId()) == null) throw new IllegalArgumentException("user not in database");
        if (postRepo.findPostById(comment.getPost().getId()) == null) throw new IllegalArgumentException("post not in database");
        comment.setUser(userRepo.findUserById(comment.getUser().getId()));
        comment.setPost(postRepo.findPostById(comment.getPost().getId()));
        return commentRepository.save(comment);
    }

    @Override
    public int getAmountOfParticipantsByPostId(Long postId) {
        List<User> uniqueUsers = new ArrayList<>();
        for (Comment comment : findCommentsByPostId(postId)) {
            if (!uniqueUsers.contains(comment.getUser())) uniqueUsers.add(comment.getUser());
        }
        return uniqueUsers.size();
    }
}
