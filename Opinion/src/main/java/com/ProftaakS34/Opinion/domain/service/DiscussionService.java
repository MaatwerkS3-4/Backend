package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.repository.DiscussionRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DiscussionService {

    private final DiscussionRepository discussionRepository;

    private final UserService userService;

    private final DiscussionMapper discussionMapper;

    @Autowired
    public DiscussionService(DiscussionRepository discussionRepository, UserService userService, CommentService commentService, DiscussionMapper discussionMapper, UserMapper userMapper, CommentMapper commentMapper) {
        this.discussionRepository = discussionRepository;
        this.userService = userService;
        this.discussionMapper = discussionMapper;
    }

    public Discussion findDiscussionById(Long id) {
        return discussionMapper.toModel(discussionRepository.findById(id).orElse(null));
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

    public Discussion postDiscussion(long userId, String subject, String description, List<String> tags) {
        if(subject == null || subject.isEmpty()) throw new IllegalArgumentException("subject is null or empty");
        if(description == null || description.isEmpty()) throw new IllegalArgumentException("description is null or empty");
        List<String> tagsFiltered = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            if (tags.size() > 3) throw new IllegalArgumentException("More than 3 tags");
            for (String t : tags) {
                if (!t.isBlank() && !t.isEmpty()) tagsFiltered.add(t.toLowerCase());
            }
        }
        User poster = userService.findUserById(userId);
        if(poster == null) throw new IllegalArgumentException("user is null or incorrect");

        Discussion model = new Discussion(subject, description, poster, tagsFiltered);
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
