package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, UserMapper userMapper, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    public Comment findCommentById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            return commentMapper.toModel(commentRepository.findById(id).get());
        }
        else{
            return null;
        }
    }

    public Comment saveComment(long posterId, String content) {
        //Check content
        if(content == null || content.isEmpty() || content.isBlank())
            throw new IllegalArgumentException("Invalid content");

        //Find user
        User poster = userService.findUserById(posterId);
        if(poster == null) throw new IllegalArgumentException("User not found");

        Comment model = new Comment(content, poster);
        CommentDAO comment = commentRepository.save(commentMapper.toDAO(model));
        return commentMapper.toModel(comment);
    }

    public int getNumberOfParticipantsByDiscussion(Discussion discussion) {

        List<User> uniqueUserDAOS = new ArrayList<>();
        for (Comment comment: discussion.getComments()) {
            if (!uniqueUserDAOS.contains(comment.getPoster())) uniqueUserDAOS.add(comment.getPoster());
        }

        return uniqueUserDAOS.size();
    }
}
