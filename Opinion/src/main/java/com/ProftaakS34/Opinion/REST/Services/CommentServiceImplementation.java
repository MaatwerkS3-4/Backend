package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import com.ProftaakS34.Opinion.Data.Repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImplementation(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment findCommentByPostId(Long postId) {
        return null;
    }

    @Override
    public List<Comment> findAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
