package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Mocks.MockCommentRepo;
import com.ProftaakS34.Opinion.Mocks.MockDiscussionRepo;
import com.ProftaakS34.Opinion.Mocks.MockUserRepo;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.domain.service.PasswordService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class DiscussionServiceTests {
    private DiscussionService discussionService;
    private UserDAO correctUserDAO;
    private List<Category> discussionTags;


    @BeforeEach
    void setUp() throws Exception {
        MockUserRepo mockUserRepo = new MockUserRepo();
        UserMapper userMapper = new UserMapper();
        PasswordService passwordService = new PasswordService();
        MockCommentRepo mockCommentRepo = new MockCommentRepo();
        CommentMapper commentMapper = new CommentMapper(userMapper);
        MockDiscussionRepo mockDiscussionRepo = new MockDiscussionRepo();
        DiscussionMapper discussionMapper = new DiscussionMapper(userMapper, commentMapper);

        UserService userService = new UserService(mockUserRepo, userMapper, passwordService);
        CommentService commentService = new CommentService(mockCommentRepo, userService, userMapper, commentMapper, mockDiscussionRepo);

        discussionService = new DiscussionService(mockDiscussionRepo, userService, discussionMapper, userMapper);

        discussionTags = new ArrayList<>();
        discussionTags.add(Category.HEALTH);

        correctUserDAO = new UserDAO("username", "password");
        correctUserDAO.setId(0);
        userService.saveUser(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());
    }


    @Test
    void findByIdNotExists() {
        long searchFor = 1;

        Assertions.assertThrows(NullPointerException.class, (Executable) discussionService.findDiscussionById(searchFor));
    }

    @Test
    void findByIdExists() {
        DiscussionDAO discussionDAO = DAOSetter(correctUserDAO, "Subject", "Description", discussionTags);
        discussionDAO.setId(0);

        long searchFor = 0;

        Discussion pulledDiscussion = discussionService.findDiscussionById(searchFor);

        Assertions.assertEquals(discussionDAO.getId(), pulledDiscussion.getId());
        Assertions.assertEquals(discussionDAO.getSubject(), pulledDiscussion.getSubject());
    }

    @Test
    void findBySubjectNotExists() {
        Assertions.assertThrows(NullPointerException.class, (Executable) discussionService.findDiscussionBySubject("Fake"));
    }

    @Test
    void findBySubjectExists() {
        DiscussionDAO discussionDAO = DAOSetter(correctUserDAO, "Subject", "Description", discussionTags);
        discussionDAO.setId(0);

        Discussion pulledDiscussion = discussionService.findDiscussionBySubject("Subject");

        Assertions.assertEquals(discussionDAO.getId(), pulledDiscussion.getId());
        Assertions.assertEquals(discussionDAO.getSubject(), pulledDiscussion.getSubject());

    }

    @Test
    void UpvoteTest() throws NotFoundException {
        DiscussionDAO discussionDAO = DAOSetter(correctUserDAO, "Subject", "Description", discussionTags);
        discussionDAO.setId(0);

        long findId = 0;

        discussionService.upvoteDiscussion(0, 0);
        Discussion pulledDiscussion = discussionService.findDiscussionById(findId);

        Assertions.assertEquals(1, pulledDiscussion.getUpvoters().size());
    }

    @Test
    void findByPosterNotExists() throws NotFoundException {
        Assertions.assertThrows(NoSuchElementException.class, () -> discussionService.findDiscussionsByPoster(1));
    }

    @Test
    void findByPosterExists() throws NotFoundException {
        DiscussionDAO discussionDAO = DAOSetter(correctUserDAO, "Subject", "Description", discussionTags);
        discussionDAO.setId(0);

        long findId = 0;

        List<Discussion> pulledDiscussions = discussionService.findDiscussionsByPoster(0);

        Assertions.assertEquals(1, pulledDiscussions.size());
    }



    private DiscussionDAO DAOSetter(UserDAO poster, String subject, String description, List<Category> tags) {
        DiscussionDAO newDAO = new DiscussionDAO();

        newDAO.setPoster(poster);
        newDAO.setSubject(subject);
        newDAO.setDescription(description);
        newDAO.setTags(tags);

        discussionService.postDiscussion(newDAO.getPoster().getId(), newDAO.getSubject(), newDAO.getDescription(), newDAO.getTags());

        return newDAO;
    }
}
