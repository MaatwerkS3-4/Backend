package com.ProftaakS34.Opinion.domain.mapper;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.DiscussionDTO;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        dao.setComments(comments);
        dao.setId(model.getId());
        dao.setPoster(poster);
        dao.setSubject(model.getSubject());

        return dao;
    }

    public Discussion toModel(DiscussionDAO dao){
        User poster = userMapper.toModel(dao.getPoster());
        List<Comment> comments = new ArrayList<>();
        for(CommentDAO c : dao.getComments()){
            comments.add(commentMapper.toModel(c));
        }

        Discussion model = new Discussion();
        model.setComments(comments);
        model.setId(dao.getId());
        model.setSubject(dao.getSubject());
        model.setPoster(poster);

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
        dto.setId(model.getId());
        dto.setPoster(poster);
        dto.setSubject(model.getSubject());
        return dto;
    }
}
