package com.ProftaakS34.Opinion.domain.mapper;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Post;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import com.ProftaakS34.Opinion.web.api.dto.PostDTO;
import com.ProftaakS34.Opinion.web.api.dto.PostInfoDTO;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public PostMapper(UserMapper userMapper, CommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }


    public PostDAO toDAO(Post model){
        UserDAO poster = userMapper.toDAO(model.getPoster());
        List<CommentDAO> comments = new ArrayList<>();
        for(Comment c : model.getComments()){
            comments.add(commentMapper.toDAO(c));
        }

        PostDAO dao = new PostDAO();
        dao.setComments(comments);
        dao.setId(model.getId());
        dao.setPoster(poster);
        dao.setSubject(model.getSubject());

        return dao;
    }

    public Post toModel(PostDAO dao){
        User poster = userMapper.toModel(dao.getPoster());
        List<Comment> comments = new ArrayList<>();
        for(CommentDAO c : dao.getComments()){
            comments.add(commentMapper.toModel(c));
        }

        Post model = new Post();
        model.setComments(comments);
        model.setId(dao.getId());
        model.setSubject(dao.getSubject());
        model.setPoster(poster);

        return model;
    }

    public PostDTO toDTO(Post model){
        UserDTO poster = userMapper.toDTO(model.getPoster());
        List<CommentDTO> comments = new ArrayList<>();
        for(Comment c : model.getComments()){
            comments.add(commentMapper.toDTO(c));
        }

        PostDTO dto = new PostDTO();
        dto.setComments(comments);
        dto.setId(model.getId());
        dto.setPoster(poster);
        dto.setSubject(model.getSubject());
        return dto;
    }
}
