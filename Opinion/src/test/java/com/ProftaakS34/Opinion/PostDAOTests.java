package com.ProftaakS34.Opinion;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostDAOTests {
//    public PostService postRepo;
//    public UserService userRepo;
//    public UserDAO correctUserDAO;
//    @BeforeEach
//    public void setUp(){
//        userRepo = new UserService(new MockUserRepo());
//        postRepo = new PostService(new MockPostRepo(), userRepo);
//        correctUserDAO = new UserDAO("username", "password");
//        correctUserDAO.setId(1);
//        userRepo.saveUser(correctUserDAO);
//    }
//    @Test
//    public void checksIfPostSubjectExists(){
//        PostDAO postDAOMissingSubject = new PostDAO();
//        postDAOMissingSubject.setUser(correctUserDAO);
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            postRepo.savePost(postDAOMissingSubject);
//        });
//    }
//    @Test
//    public void checksIfUserExists(){
//        PostDAO postDAOMissingUser = new PostDAO();
//        postDAOMissingUser.setSubject("politics");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            postRepo.savePost(postDAOMissingUser);
//        });
//    }
//    @Test
//    public void checksIfUserIsInDatabase() {
//        PostDAO postDAOIncorrectUser = new PostDAO();
//        postDAOIncorrectUser.setSubject("politics");
//        postDAOIncorrectUser.setUser(new UserDAO("username", "password"));
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
//            postRepo.savePost(postDAOIncorrectUser);
//        } );
//    }
//    @Test
//    public void canCreatePost() {
//        PostDAO correctPostDAO = new PostDAO("politics", correctUserDAO);
//        postRepo.savePost(correctPostDAO);
//        Assertions.assertSame(postRepo.findAllPosts().get(0), correctPostDAO);
//    }
}
