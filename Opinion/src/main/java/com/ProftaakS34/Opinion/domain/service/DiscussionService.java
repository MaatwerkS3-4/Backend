package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.repository.DiscussionRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        discussions.sort(Comparator.comparing(Discussion::getTimeStamp).reversed());
        return discussions;
    }

    public Discussion postDiscussion(long userId, String subject, String description, List<Category> tags) {
        if(subject == null || subject.isEmpty()) throw new IllegalArgumentException("subject is null or empty");
        if(description == null || description.isEmpty()) throw new IllegalArgumentException("description is null or empty");

        if(tags.size() > 3) throw new IllegalArgumentException("More than 3 tags provided");
        if(tags.size() < 1) throw new IllegalArgumentException("No tags provided");

        User poster = userService.findUserById(userId);
        if(poster == null) throw new IllegalArgumentException("user is null or incorrect");
        Discussion model = new Discussion(subject, description, poster, tags);
        DiscussionDAO dao = discussionRepository.save(discussionMapper.toDAO(model));
        return discussionMapper.toModel(dao);
    }

    private void sortReplies(Comment comment){
        for(Comment c: comment.getReplies()){
            sortReplies(c);
        }

        comment.getReplies().sort(Comparator.comparing(Comment::getTimeStamp).reversed());
    }
}
