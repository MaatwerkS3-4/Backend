package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Mocks.MockCommentRepo;
import com.ProftaakS34.Opinion.Mocks.MockDiscussionRepo;
import com.ProftaakS34.Opinion.Mocks.MockPasswordService;
import com.ProftaakS34.Opinion.Mocks.MockUserRepo;
import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class CommentDAOTests {
    private CommentService commentRepo;
    private DiscussionService discussionRepo;

    private UserDAO correctUserDAO;
    private DiscussionDAO correctDiscDAO;

    @BeforeEach
    void setUp() throws Exception {
        UserMapper userMapper = new UserMapper();
        CommentMapper commentMapper = new CommentMapper(userMapper);
        MockDiscussionRepo mockRepo = new MockDiscussionRepo();

        UserService userRepo = new UserService(new MockUserRepo(), userMapper, new MockPasswordService());
        discussionRepo = new DiscussionService(mockRepo, userRepo, new DiscussionMapper(userMapper, commentMapper), userMapper);
        commentRepo = new CommentService(new MockCommentRepo(), userRepo, userMapper, commentMapper, mockRepo);


        //Setup UserDAO and save it
        correctUserDAO = new UserDAO("username", "password");
        correctUserDAO.setId(0);
        userRepo.saveUser(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());


        //Setup DiscussionDAO and post it
        correctDiscDAO = new DiscussionDAO();
        correctDiscDAO.setId(0);
        correctDiscDAO.setPoster(correctUserDAO);
        correctDiscDAO.setSubject("Subject");
        correctDiscDAO.setDescription("Description");
            List<Category> tagList = new ArrayList<>();
            tagList.add(Category.HEALTH);
        correctDiscDAO.setTags(tagList);


        discussionRepo.postDiscussion(correctUserDAO.getId(), correctDiscDAO.getSubject(), correctDiscDAO.getDescription(), correctDiscDAO.getTags());
    }


    @Test
    void checksIfCommentHasContent() {
        CommentDAO commentDAOMissingContent = new CommentDAO();
        commentDAOMissingContent.setPoster(correctUserDAO);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            commentRepo.saveComment(correctDiscDAO.getId(),
                    commentDAOMissingContent.getPoster().getId(),
                    commentDAOMissingContent.getContent());
        });
    }

    @Test
    void checksIfCommentHasPost() {
        CommentDAO commentDAOMissingPost = new CommentDAO();
        commentDAOMissingPost.setPoster(correctUserDAO);
        commentDAOMissingPost.setContent("I agree");
        Long fakePostID = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            commentRepo.saveComment(fakePostID,
                    commentDAOMissingPost.getPoster().getId(),
                    commentDAOMissingPost.getContent());
        });
    }

    @Test
    void checksIfCommentHasUser(){
        CommentDAO commentDAOMissingUser = new CommentDAO();
        commentDAOMissingUser.setContent("I agree");

        Assertions.assertThrows(NullPointerException.class, () -> {
           commentRepo.saveComment(correctDiscDAO.getId(),
                   commentDAOMissingUser.getPoster().getId(),
                   commentDAOMissingUser.getContent());
        });
    }

    @Test
    void checksIfPostExists() {
        CommentDAO commentDAOPostNotInDatabase = new CommentDAO();
        commentDAOPostNotInDatabase.setContent("I agree");
        commentDAOPostNotInDatabase.setPoster(correctUserDAO);
        long fakePostID = 5;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            commentRepo.saveComment(fakePostID,
                    commentDAOPostNotInDatabase.getPoster().getId(),
                    commentDAOPostNotInDatabase.getContent());
        });
    }

    @Test
    void checksIfUserExists() {
        CommentDAO commentDAOUserNotInDatabase = new CommentDAO();
        commentDAOUserNotInDatabase.setContent("I agree");

        UserDAO fakeUserDAO = new UserDAO();
        fakeUserDAO.setId(5);
        commentDAOUserNotInDatabase.setPoster(fakeUserDAO);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            commentRepo.saveComment(correctDiscDAO.getId(),
                    commentDAOUserNotInDatabase.getPoster().getId(),
                    commentDAOUserNotInDatabase.getContent());
        });
    }

    @Test
    void canPlaceComment() {
        CommentDAO correctCommentDAO = new CommentDAO();
        correctCommentDAO.setPoster(correctUserDAO);
        correctCommentDAO.setContent("I agree");

        commentRepo.saveComment(correctDiscDAO.getId(),
                correctCommentDAO.getPoster().getId(),
                correctCommentDAO.getContent());

        Discussion wantedDiscussion = discussionRepo.findAllDiscussions().get(0);
        Comment wantedComment = wantedDiscussion.getComments().get(0);

        Assertions.assertSame(wantedComment.getPoster().getId(), correctCommentDAO.getPoster().getId());
        Assertions.assertSame(wantedComment.getContent(), correctCommentDAO.getContent());
    }
}
