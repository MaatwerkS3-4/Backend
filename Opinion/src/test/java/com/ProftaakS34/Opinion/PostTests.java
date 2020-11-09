package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.UserRepository;
import com.ProftaakS34.Opinion.REST.Services.PostServiceImplementation;
import com.ProftaakS34.Opinion.REST.Services.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

@SpringBootTest
public class PostTests {
    public PostServiceImplementation postRepo;
    public UserServiceImplementation userRepo;
    public User correctUser;
    @BeforeEach
    public void setUp(){
        userRepo = new UserServiceImplementation(new MockUserRepo());
        postRepo = new PostServiceImplementation(new MockPostRepo(), userRepo);
        correctUser = new User("username", "password");
        correctUser.setId(1);
        userRepo.saveUser(correctUser);
    }
    @Test
    public void checksIfPostSubjectExists(){
        Post postMissingSubject = new Post();
        postMissingSubject.setUser(correctUser);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postRepo.savePost(postMissingSubject);
        });
    }
    @Test
    public void checksIfUserExists(){
        Post postMissingUser = new Post();
        postMissingUser.setSubject("politics");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            postRepo.savePost(postMissingUser);
        });
    }
    @Test
    public void checksIfUserIsInDatabase() {
        Post postIncorrectUser = new Post();
        postIncorrectUser.setSubject("politics");
        postIncorrectUser.setUser(new User("username", "password"));
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            postRepo.savePost(postIncorrectUser);
        } );
    }
    @Test
    public void canCreatePost() {
        Post correctPost = new Post("politics", correctUser);
        postRepo.savePost(correctPost);
        Assertions.assertSame(postRepo.findAllPosts().get(0), correctPost);
    }
}
