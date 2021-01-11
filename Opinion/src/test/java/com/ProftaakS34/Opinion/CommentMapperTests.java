package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.CommentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
public class CommentMapperTests {
    public CommentMapper mapper;
    public CommentDAO comDAO;
    public CommentDTO comDTO;
    public Comment comment;

    private CommentMapperTests() {
        comDAO = new CommentDAO();
        comDAO.setId(1);
        comDAO.setContent("Content");
            UserDAO userDAO = new UserDAO();
            userDAO.setId(1);
            userDAO.setUsername("A");
            userDAO.setEncryptedPassword("B");
        comDAO.setPoster(userDAO);
            Date date = new Date(System.currentTimeMillis());
        comDAO.setTimeStamp(date);
        comDAO.setReplies(new ArrayList<>());

        comDTO = new CommentDTO();
        comDTO.setId(1);
        comDTO.setContent("Content");
        comDTO.setTimeStamp(date);
        comDTO.setReplies(new ArrayList<>());

        comment = new Comment();
        comment.setId(1);
        comment.setContent("Content");
            User user = new User();
            user.setId(1);
            user.setUsername("A");
            user.setPassword("A");
        comment.setPoster(user);
        comment.setTimeStamp(date);
        comment.setReplies(new ArrayList<>());
    }


    @BeforeEach
    public void SetUp() {
        UserMapper userMapper = new UserMapper();
        mapper = new CommentMapper(userMapper);
    }


    @Test
    public void ToDAOTest() {
        CommentDAO newDAO = mapper.toDAO(comment);

        Assertions.assertEquals(comDAO.getId(), newDAO.getId());
        Assertions.assertEquals(comDAO.getContent(), newDAO.getContent());
        Assertions.assertEquals(comDAO.getPoster().getUsername(), newDAO.getPoster().getUsername());
    }

    @Test
    public void toModelTest() {
        Comment newComment = mapper.toModel(comDAO);

        Assertions.assertEquals(comment.getId(), newComment.getId());
        Assertions.assertEquals(comment.getContent(), newComment.getContent());
        Assertions.assertEquals(comment.getPoster().getUsername(), newComment.getPoster().getUsername());
    }

    @Test
    public void toDTOTest() {
        CommentDTO newDTO = mapper.toDTO(comment);

        Assertions.assertEquals(comDTO.getId(), newDTO.getId());
        Assertions.assertEquals(comDTO.getContent(), newDTO.getContent());
    }
}
