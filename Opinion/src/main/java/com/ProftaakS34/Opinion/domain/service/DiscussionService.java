package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.DiscussionRepository;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;

    private final UserService userService;

    private final DiscussionMapper discussionMapper;
    private final UserMapper userMapper;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, UserService userService, DiscussionMapper discussionMapper, UserMapper userMapper) {
        this.discussionRepository = discussionRepository;
        this.userService = userService;
        this.discussionMapper = discussionMapper;
        this.userMapper = userMapper;
    }

    public Discussion findDiscussionById(Long id) {
        if (discussionRepository.findById(id).orElse(null) != null) {
            return discussionMapper.toModel(Objects.requireNonNull(discussionRepository.findById(id).orElse(null)));
        }
        return null;
    }

    public Discussion findDiscussionBySubject(String subject) {

        for(DiscussionDAO discussionDAO : discussionRepository.findAll()){
            if(discussionDAO.getSubject().toLowerCase().equals(subject.toLowerCase())){
                return discussionMapper.toModel(discussionDAO);
            }
        }
        return null;
    }

    public List<Discussion> findAllDiscussions() {
        List<Discussion> discussions = new ArrayList<>();
        for(DiscussionDAO p : discussionRepository.findAll()){
            discussions.add(discussionMapper.toModel(p));
        }

        Collections.shuffle(discussions);
        return discussions;
    }

    public Discussion postDiscussion(long userId, String subject, String description, List<Category> tags) {
        if(subject == null || subject.isEmpty()) throw new IllegalArgumentException("subject is null or empty");

        if(tags.size() > 3) throw new IllegalArgumentException("More than 3 tags provided");
        if(tags.isEmpty()) throw new IllegalArgumentException("No tags provided");

        User poster = userService.findUserById(userId);
        if(poster == null) throw new IllegalArgumentException("user is null or incorrect");
        Discussion model = new Discussion(subject, description, poster, tags);
        DiscussionDAO dao = discussionRepository.save(discussionMapper.toDAO(model));
        return discussionMapper.toModel(dao);
    }

    public void upvoteDiscussion(long discussionId, long userId) throws NotFoundException {
        DiscussionDAO discussion = discussionRepository.findById(discussionId).get();
        UserDAO user = userMapper.toDAO(userService.findUserById(userId));
        if (user == null) throw new NotFoundException("User not found");
        discussion.getDiscussionUpvoters().add(user);
        discussionRepository.save(discussion);
    }

    public List<Discussion> findDiscussionsByPoster (long userId) throws NotFoundException {
        UserDAO user = userMapper.toDAO(userService.findUserById(userId));
        if (user == null) throw new NotFoundException("User not found");
        List<Discussion> discussions = new ArrayList<>();
        for (DiscussionDAO discussionDAO : discussionRepository.findAll()) {
            if (discussionDAO.getPoster().getId() == userId) discussions.add(discussionMapper.toModel(discussionDAO));
        }
        return discussions;
    }
}
