package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.service.CommentServiceImplementation;
import com.ProftaakS34.Opinion.domain.service.PostServiceImplementation;
import com.ProftaakS34.Opinion.domain.service.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
public class CommentTests {
    public CommentServiceImplementation commentRepo;
    public UserServiceImplementation userRepo;
    public PostServiceImplementation postRepo;
    UserDAO correctUserDAO;
    PostDAO correctPostDAO;
    @BeforeEach
    public void setUp() {
        userRepo = new UserServiceImplementation(new MockUserRepo());
        postRepo = new PostServiceImplementation(new MockPostRepo(), userRepo);
        commentRepo = new CommentServiceImplementation(new MockCommentRepo(), userRepo, postRepo);
        correctUserDAO = new UserDAO("username", "password");
        correctUserDAO.setId(1);
        userRepo.saveUser(correctUserDAO);
        correctPostDAO = new PostDAO("politics", correctUserDAO);
        correctPostDAO.setId(1);
        postRepo.savePost(correctPostDAO);
    }
    @Test
    public void checksIfCommentHasContent() {
        CommentDAO commentDAOMissingContent = new CommentDAO();
        commentDAOMissingContent.setUser(correctUserDAO);
        commentDAOMissingContent.setPost(correctPostDAO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            commentRepo.saveComment(commentDAOMissingContent);
        });
    }
    @Test
    public void checksIfCommentHasPost() {
        CommentDAO commentDAOMissingPost = new CommentDAO();
        commentDAOMissingPost.setUser(correctUserDAO);
        commentDAOMissingPost.setContent("I agree");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            commentRepo.saveComment(commentDAOMissingPost);
        });
    }
    @Test
    public void checksIfCommentHasUser(){
        CommentDAO commentDAOMissingUser = new CommentDAO();
        commentDAOMissingUser.setPost(correctPostDAO);
        commentDAOMissingUser.setContent("I agree");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           commentRepo.saveComment(commentDAOMissingUser);
        });
    }
    @Test
    public void checksIfPostExists() {
        CommentDAO commentDAOPostNotInDatabase = new CommentDAO();
        commentDAOPostNotInDatabase.setContent("I agree");
        commentDAOPostNotInDatabase.setUser(correctUserDAO);
        PostDAO fakePostDAO = new PostDAO();
        commentDAOPostNotInDatabase.setPost(fakePostDAO);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            commentRepo.saveComment(commentDAOPostNotInDatabase);
        });
    }
    @Test
    public void checksIfUserExists() {
        CommentDAO commentDAOUserNotInDatabase = new CommentDAO();
        commentDAOUserNotInDatabase.setPost(correctPostDAO);
        commentDAOUserNotInDatabase.setContent("I agree");
        UserDAO fakeUserDAO = new UserDAO();
        commentDAOUserNotInDatabase.setUser(fakeUserDAO);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            commentRepo.saveComment(commentDAOUserNotInDatabase);
        });
    }
    @Test
    public void canPlaceComment() {
        CommentDAO correctCommentDAO = new CommentDAO();
        correctCommentDAO.setPost(correctPostDAO);
        correctCommentDAO.setUser(correctUserDAO);
        correctCommentDAO.setContent("I agree");
        commentRepo.saveComment(correctCommentDAO);
        Assertions.assertSame(commentRepo.findAllComments().get(0), correctCommentDAO);
    }
}
