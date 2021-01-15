package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.DiscussionDTO;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DiscussionMapperTests {
    private DiscussionMapper mapper;
    private DiscussionDAO discDAO;
    private DiscussionDTO discDTO;
    private Discussion discussion;
    private User user;

    private DiscussionMapperTests() {
        discDAO = new DiscussionDAO();
        discDAO.setId(1);
        discDAO.setSubject("Subject");
        discDAO.setDescription("Description");
            UserDAO userDAO = new UserDAO();
            userDAO.setId(1);
            userDAO.setUsername("A");
            userDAO.setEncryptedPassword("B");
        discDAO.setPoster(userDAO);
            Date date = new Date(System.currentTimeMillis());
        discDAO.setTimeStamp(date);
        discDAO.setComments(new ArrayList<>());
        discDAO.setTags(new ArrayList<>());

        discDTO = new DiscussionDTO();
        discDTO.setId(1);
        discDTO.setSubject("Subject");
        discDTO.setDescription("Description");
        discDTO.setComments(new ArrayList<>());
        discDTO.setTags(new ArrayList<>());

        discussion = new Discussion();
        discussion.setId(1);
        discussion.setSubject("Subject");
        discussion.setDescription("Description");
            user = new User();
            user.setId(1);
            user.setUsername("A");
            user.setPassword("A");
        discussion.setPoster(user);
        discussion.setTimeStamp(date);
        discussion.setComments(new ArrayList<>());
        discussion.setTags(new ArrayList<>());
    }


    @BeforeEach
    void SetUp() {
        UserMapper userMapper = new UserMapper();
        CommentMapper commentMapper = new CommentMapper(userMapper);

        mapper = new DiscussionMapper(userMapper, commentMapper);
    }

    @Test
    void ToDAOTest() {
        DiscussionDAO newDAO = mapper.toDAO(discussion);

        Assertions.assertEquals(discDAO.getId(), newDAO.getId());
        Assertions.assertEquals(discDAO.getSubject(), newDAO.getSubject());
        Assertions.assertEquals(discDAO.getDescription(), newDAO.getDescription());
    }

    @Test
    void toModelTest() {
        Discussion newDisc = mapper.toModel(discDAO);

        Assertions.assertEquals(discussion.getId(), newDisc.getId());
        Assertions.assertEquals(discussion.getSubject(), newDisc.getSubject());
        Assertions.assertEquals(discussion.getDescription(), newDisc.getDescription());
    }

    @Test
    void toDTOTest() {
        DiscussionDTO newDTO = mapper.toDTO(discussion);

        Assertions.assertEquals(discDTO.getId(), newDTO.getId());
        Assertions.assertEquals(discDTO.getSubject(), newDTO.getSubject());
        Assertions.assertEquals(discDTO.getDescription(), newDTO.getDescription());
    }

    @Test
    void toDTOWithUidFromOtherUser() {
        List<Comment> commentList = new ArrayList<>();

        User otherUser = new User();
        user.setId(3);
        user.setUsername("B");
        user.setPassword("B");

        Comment otherUserComment = new Comment();
        otherUserComment.setId(1);
        otherUserComment.setPoster(user);
        otherUserComment.setContent("Content");
        otherUserComment.setReplies(new ArrayList<>());

        List<User> upVoters = new ArrayList<>();
        upVoters.add(otherUser);
        otherUserComment.setUpvoters(upVoters);

        commentList.add(otherUserComment);
        discussion.setComments(commentList);



        DiscussionDTO newDTO = mapper.toDTO(discussion, 1);

        Assertions.assertEquals(discDTO.getId(), newDTO.getId());
        Assertions.assertEquals(discDTO.getSubject(), newDTO.getSubject());
        Assertions.assertEquals(discDTO.getDescription(), newDTO.getDescription());
        Assertions.assertFalse(newDTO.getComments().get(0).isUpvotedByUser());
    }

}
