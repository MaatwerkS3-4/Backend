package com.ProftaakS34.Opinion.domain.mapper;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.DiscussionDTO;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DiscussionMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public DiscussionMapper(UserMapper userMapper, CommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }


    public DiscussionDAO toDAO(Discussion model){
        UserDAO poster = userMapper.toDAO(model.getPoster());
        List<CommentDAO> comments = new ArrayList<>();
        for(Comment c : model.getComments()){
            comments.add(commentMapper.toDAO(c));
        }

        DiscussionDAO dao = new DiscussionDAO();

        List<UserDAO> users = new ArrayList<>();
        for (User upvoter : model.getUpvoters()) {
            users.add(userMapper.toDAO(upvoter));
        }
        dao.setDiscussionUpvoters(users);

        dao.setComments(comments);
        dao.setId(model.getId());
        dao.setPoster(poster);
        dao.setSubject(model.getSubject());
        dao.setDescription(model.getDescription());
        dao.setTimeStamp(model.getTimeStamp());
        dao.setTags(model.getTags());

        return dao;
    }

    public Discussion toModel(DiscussionDAO dao){
        User poster = userMapper.toModel(dao.getPoster());
        List<Comment> comments = new ArrayList<>();
        for(CommentDAO c : dao.getComments()){
            comments.add(commentMapper.toModel(c));
        }

        comments.sort(Comparator.comparing(Comment::getTimeStamp).reversed());

        Discussion model = new Discussion();

        List<User> users = new ArrayList<>();
        for (UserDAO discussionUpvoter : dao.getDiscussionUpvoters()) {
            users.add(userMapper.toModel(discussionUpvoter));
        }
        model.setUpvoters(users);

        model.setComments(comments);
        model.setId(dao.getId());
        model.setSubject(dao.getSubject());
        model.setPoster(poster);
        model.setDescription(dao.getDescription());
        model.setTimeStamp(dao.getTimeStamp());
        model.setTags(dao.getTags());

        return model;
    }

    public DiscussionDTO toDTO(Discussion model){
        UserDTO poster = userMapper.toDTO(model.getPoster());
        List<CommentDTO> comments = new ArrayList<>();
        for(Comment c : model.getComments()){
            comments.add(commentMapper.toDTO(c));
        }

        DiscussionDTO dto = new DiscussionDTO();
        dto.setComments(comments);
        dto.setDescription(model.getDescription());
        dto.setId(model.getId());
        dto.setSubject(model.getSubject());
        dto.setTags(model.getTags());
        return dto;
    }

    public DiscussionDTO toDTO(Discussion model, long uid) {
        UserDTO poster = userMapper.toDTO(model.getPoster());
        List<CommentDTO> comments = new ArrayList<>();
        for(Comment c : model.getComments()){
            CommentDTO comment = commentMapper.toDTO(c);
            for (User upvoter : c.getUpvoters()) {
                if (upvoter.getId() == uid) comment.setUpvotedByUser(true);
            }
            comments.add(comment);
        }
        Collections.shuffle(comments);
        DiscussionDTO dto = new DiscussionDTO();
        dto.setComments(comments);
        dto.setDescription(model.getDescription());
        dto.setId(model.getId());
        dto.setSubject(model.getSubject());
        dto.setTags(model.getTags());
        return dto;
    }
}
