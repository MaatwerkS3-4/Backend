package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.Comment;

import java.util.List;

public interface CommentService {

    Comment findCommentById(Long id);

    List<Comment> findCommentsByPostId(Long postId);

    List<Comment> findAllComments();

    Comment saveComment(Comment comment);

    int getAmountOfParticipantsByPostId(Long postId);
}
