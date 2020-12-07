package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;

import java.util.List;

public interface CommentService {

    CommentDAO findCommentById(Long id);

    List<CommentDAO> findCommentsByPostId(Long postId);

    List<CommentDAO> findAllComments();

    CommentDAO saveComment(CommentDAO commentDAO);

    int getAmountOfParticipantsByPostId(Long postId);
}
