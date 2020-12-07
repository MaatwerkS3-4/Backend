package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.service.PostServiceImplementation;
import com.ProftaakS34.Opinion.domain.service.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
public class PostDAOTests {
    public PostServiceImplementation postRepo;
    public UserServiceImplementation userRepo;
    public UserDAO correctUserDAO;
    @BeforeEach
    public void setUp(){
        userRepo = new UserServiceImplementation(new MockUserRepo());
        postRepo = new PostServiceImplementation(new MockPostRepo(), userRepo);
        correctUserDAO = new UserDAO("username", "password");
        correctUserDAO.setId(1);
        userRepo.saveUser(correctUserDAO);
    }
    @Test
    public void checksIfPostSubjectExists(){
        PostDAO postDAOMissingSubject = new PostDAO();
        postDAOMissingSubject.setUser(correctUserDAO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postRepo.savePost(postDAOMissingSubject);
        });
    }
    @Test
    public void checksIfUserExists(){
        PostDAO postDAOMissingUser = new PostDAO();
        postDAOMissingUser.setSubject("politics");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postRepo.savePost(postDAOMissingUser);
        });
    }
    @Test
    public void checksIfUserIsInDatabase() {
        PostDAO postDAOIncorrectUser = new PostDAO();
        postDAOIncorrectUser.setSubject("politics");
        postDAOIncorrectUser.setUser(new UserDAO("username", "password"));
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            postRepo.savePost(postDAOIncorrectUser);
        } );
    }
    @Test
    public void canCreatePost() {
        PostDAO correctPostDAO = new PostDAO("politics", correctUserDAO);
        postRepo.savePost(correctPostDAO);
        Assertions.assertSame(postRepo.findAllPosts().get(0), correctPostDAO);
    }
}
