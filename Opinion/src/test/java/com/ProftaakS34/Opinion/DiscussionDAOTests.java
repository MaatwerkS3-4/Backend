package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Mocks.MockDiscussionRepo;
import com.ProftaakS34.Opinion.Mocks.MockPasswordService;
import com.ProftaakS34.Opinion.Mocks.MockUserRepo;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.DiscussionMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
public class DiscussionDAOTests {
    public DiscussionService discussionRepo;
    public UserService userRepo;
    public UserMapper userMapper;
    public CommentMapper commentMapper;

    public UserDAO correctUserDAO;
    public List<Category> discussionTags;


    @BeforeEach
    public void setUp() throws Exception {
        userMapper = new UserMapper();
        commentMapper = new CommentMapper(userMapper);

        userRepo = new UserService(new MockUserRepo(), userMapper, new MockPasswordService());

        correctUserDAO = new UserDAO("username", "password");
        correctUserDAO.setId(0);
        userRepo.saveUser(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());

        discussionRepo = new DiscussionService(new MockDiscussionRepo(), userRepo, null, new DiscussionMapper(userMapper, commentMapper), userMapper, commentMapper);
        discussionTags = new ArrayList<>();
        discussionTags.add(Category.Health);
    }


    @Test
    public void checksIfPostSubjectExists(){
        DiscussionDAO discussionDAOMissingSubject = DAOSetter(correctUserDAO, "", "desc", discussionTags);


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            discussionRepo.postDiscussion(discussionDAOMissingSubject.getPoster().getId(),
                    discussionDAOMissingSubject.getSubject(),
                    discussionDAOMissingSubject.getDescription(),
                    discussionDAOMissingSubject.getTags());
        });
    }

    @Test
    public void checksIfUserExists(){

        DiscussionDAO discussionDAOMissingUser = DAOSetter(null, "politics", "desc", discussionTags);

        Assertions.assertThrows(NullPointerException.class, () -> {
            discussionRepo.postDiscussion(discussionDAOMissingUser.getPoster().getId(),
                    discussionDAOMissingUser.getSubject(),
                    discussionDAOMissingUser.getDescription(),
                    discussionDAOMissingUser.getTags());
        });
    }

    @Test
    public void checksIfUserIsInDatabase() {
        UserDAO fakeUser = new UserDAO("username", "password");
        fakeUser.setId(5);

        DiscussionDAO discussionDAOIncorrectUser = DAOSetter(fakeUser, "politics", "desc", discussionTags);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            discussionRepo.postDiscussion(discussionDAOIncorrectUser.getPoster().getId(),
                    discussionDAOIncorrectUser.getSubject(),
                    discussionDAOIncorrectUser.getDescription(),
                    discussionDAOIncorrectUser.getTags());
        } );
    }

    @Test
    public void canCreatePost() {
        DiscussionDAO correctDiscussionDAO = DAOSetter(correctUserDAO, "politics", "desc", discussionTags);

        discussionRepo.postDiscussion(correctDiscussionDAO.getPoster().getId(),
                correctDiscussionDAO.getSubject(),
                correctDiscussionDAO.getDescription(),
                correctDiscussionDAO.getTags());

        Discussion pulledDiscussion = discussionRepo.findAllDiscussions().get(0);

        Assertions.assertSame(pulledDiscussion.getId(), correctDiscussionDAO.getId());
        Assertions.assertSame(pulledDiscussion.getSubject(), correctDiscussionDAO.getSubject());
        Assertions.assertSame(pulledDiscussion.getDescription(), correctDiscussionDAO.getDescription());

   }



   private DiscussionDAO DAOSetter(UserDAO poster, String subject, String description, List<Category> tags) {
        DiscussionDAO newDAO = new DiscussionDAO();

        newDAO.setPoster(poster);
        newDAO.setSubject(subject);
        newDAO.setDescription(description);
        newDAO.setTags(tags);

       return newDAO;
   }
}
