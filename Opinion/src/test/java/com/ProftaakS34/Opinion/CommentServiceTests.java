package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Mocks.MockCommentRepo;
import com.ProftaakS34.Opinion.Mocks.MockDiscussionRepo;
import com.ProftaakS34.Opinion.Mocks.MockUserRepo;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.PasswordService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class CommentServiceTests {
    private CommentService commentService;


    @BeforeEach
    void SetUp() {
        MockUserRepo mockUserRepo = new MockUserRepo();
        UserMapper userMapper = new UserMapper();
        PasswordService passwordService = new PasswordService();
        MockCommentRepo mockCommentRepo = new MockCommentRepo();
        CommentMapper commentMapper = new CommentMapper(userMapper);
        MockDiscussionRepo mockDiscussionRepo = new MockDiscussionRepo();

        UserService userService = new UserService(mockUserRepo, userMapper, passwordService);
        commentService = new CommentService(mockCommentRepo, userService, userMapper, commentMapper, mockDiscussionRepo);
    }


    @Test
    void getParticipants() {
        List<Comment> commentList = new ArrayList<>();

        Discussion discussion = new Discussion();
        discussion.setId(1);
        discussion.setSubject("Subject");
        discussion.setDescription("Description");
            User user = new User();
            user.setId(1);
            user.setUsername("A");
            user.setPassword("A");
        discussion.setPoster(user);
            Date date = new Date(System.currentTimeMillis());
        discussion.setTimeStamp(date);
        discussion.setComments(new ArrayList<>());

        User otherUser = new User();

        otherUser.setId(3);
        otherUser.setUsername("B");
        otherUser.setPassword("B");

        Comment otherUserComment = new Comment();
        otherUserComment.setId(1);
        otherUserComment.setPoster(otherUser);

        commentList.add(otherUserComment);
        discussion.setComments(commentList);


        Assertions.assertEquals(1, commentService.getNumberOfParticipantsByDiscussion(discussion));
    }
}
