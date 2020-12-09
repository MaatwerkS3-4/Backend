package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.repository.DiscussionRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.controller.DiscussionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;

    private final UserService userService;
    private final CommentService commentService;

    private final DiscussionMapper discussionMapper;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, UserService userService, CommentService commentService, DiscussionMapper discussionMapper, UserMapper userMapper, CommentMapper commentMapper) {
        this.discussionRepository = discussionRepository;
        this.userService = userService;
        this.commentService = commentService;
        this.discussionMapper = discussionMapper;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    public Discussion findDiscussionById(Long id) {
        return discussionMapper.toModel(discussionRepository.findById(id).get());
    }

    public Discussion findDiscussionBySubject(String subject) {

        for(DiscussionDAO discussionDAO : discussionRepository.findAll()){
            if(discussionDAO.getSubject().toLowerCase().equals(subject.toLowerCase())){
                return discussionMapper.toModel(discussionDAO);
            }
        }
        return null;
    }

    public List<Discussion> findDiscussionByPartialSubstring(String partialSubject) {
        List<Discussion> matches = new ArrayList<>();
        for(DiscussionDAO discussionDAO : discussionRepository.findAll()){
            if(discussionDAO.getSubject().contains(partialSubject)){
                matches.add(discussionMapper.toModel(discussionDAO));
            }
        }
        return matches;
    }

    public List<Discussion> findAllDiscussions() {
        List<Discussion> discussions = new ArrayList<>();
        for(DiscussionDAO p : discussionRepository.findAll()){
            discussions.add(discussionMapper.toModel(p));
        }

        return discussions;
    }

    public List<Discussion> findAllDiscussionsByCriteria(String criteria){
        List<Discussion> filteredDiscussions = new ArrayList<>();
        for(Discussion d : findAllDiscussions()){
            if(d.getSubject().toLowerCase().contains(criteria.toLowerCase())){
                filteredDiscussions.add(d);
            }
        }
        return filteredDiscussions;
    }

    public Discussion postDiscussion(long userId, String subject) {
        if(subject == null || subject.isEmpty()) throw new IllegalArgumentException("subject is null or empty");
        User poster = userService.findUserById(userId);
        if(poster == null) throw new IllegalArgumentException("user is null or incorrect");

        DiscussionDAO dao = discussionRepository.save(new DiscussionDAO(subject, userMapper.toDAO(poster)));
        return discussionMapper.toModel(dao);
    }

    public Comment postComment(long discussionId, long posterId, String content) {

        //Find discussion
        Optional<DiscussionDAO> promise = discussionRepository.findById(discussionId);
        if(promise.isEmpty()) throw new IllegalArgumentException("Discussion not found");
        DiscussionDAO discussion = promise.get();

        //Create message
        Comment comment = commentService.saveComment(posterId, content);

        //Add comment to discussion
        discussion.getComments().add(commentMapper.toDAO(comment));
        discussionRepository.save(discussion);

        return comment;
    }

    public List<Comment> getCommentsByDiscussionId(long discussionId){
        Discussion discussion = findDiscussionById(discussionId);
        return discussion.getComments();
    }
}