package com.ProftaakS34.Opinion.domain.mapper;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentMapper {
    private final UserMapper userMapper;

    @Autowired
    public CommentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CommentDAO toDAO(Comment model){
        UserDAO poster = userMapper.toDAO(model.getPoster());
        CommentDAO dao = new CommentDAO();
        dao.setContent(model.getContent());
        dao.setId(model.getId());
        dao.setPoster(poster);
        dao.setTimeStamp(model.getTimeStamp());

        List<CommentDAO> replies = new ArrayList<>();
        for(Comment r : model.getReplies()){
            replies.add(toDAO(r));
        }

        dao.setReplies(replies);

        return dao;
    }

    public Comment toModel(CommentDAO dao){
        User poster = userMapper.toModel(dao.getPoster());
        Comment model = new Comment();
        model.setContent(dao.getContent());
        model.setId(dao.getId());
        model.setPoster(poster);
        model.setTimeStamp(dao.getTimeStamp());

        List<Comment> replies = new ArrayList<>();
        for(CommentDAO r: dao.getReplies()){
            replies.add(toModel(r));
        }
        replies.sort(Comparator.comparing(Comment::getTimeStamp).reversed());
        model.setReplies(replies);

        return model;
    }

    public CommentDTO toDTO(Comment model){
        UserDTO poster = userMapper.toDTO(model.getPoster());
        CommentDTO dto = new CommentDTO();
        dto.setContent(model.getContent());
        dto.setId(model.getId());
        dto.setPoster(poster);
        dto.setTimeStamp(model.getTimeStamp());

        List<CommentDTO> replies = new ArrayList<>();
        for(Comment r : model.getReplies()){
            replies.add((toDTO(r)));
        }
        dto.setReplies(replies);

        return dto;
    }
}
