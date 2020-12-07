package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;
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
    public CommentDAO findCommentById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            return commentRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

    @Override
    public List<CommentDAO> findCommentsByPostId(Long postId) {
        List<CommentDAO> results = new ArrayList<>();
        for(CommentDAO commentDAO : commentRepository.findAll()){
            if(commentDAO.getPost().getId() == postId){
                results.add(commentDAO);
            }
        }
        return results;
    }

    @Override
    public List<CommentDAO> findAllComments() {
        return (List<CommentDAO>) commentRepository.findAll();
    }

    @Override
    public CommentDAO saveComment(CommentDAO commentDAO) {
        if (commentDAO.getContent() == null || commentDAO.getContent().isEmpty()) throw new IllegalArgumentException("content is empty or null");
        if (commentDAO.getPost() == null) throw new IllegalArgumentException("post is null");
        if (commentDAO.getUser() == null) throw new IllegalArgumentException("user is null");
        if (userRepo.findUserById(commentDAO.getUser().getId()) == null) throw new IllegalArgumentException("user not in database");
        if (postRepo.findPostById(commentDAO.getPost().getId()) == null) throw new IllegalArgumentException("post not in database");
        commentDAO.setUser(userRepo.findUserById(commentDAO.getUser().getId()));
        commentDAO.setPost(postRepo.findPostById(commentDAO.getPost().getId()));
        return commentRepository.save(commentDAO);
    }

    @Override
    public int getAmountOfParticipantsByPostId(Long postId) {
        List<UserDAO> uniqueUserDAOS = new ArrayList<>();
        for (CommentDAO commentDAO : findCommentsByPostId(postId)) {
            if (!uniqueUserDAOS.contains(commentDAO.getUser())) uniqueUserDAOS.add(commentDAO.getUser());
        }
        return uniqueUserDAOS.size();
    }
}
