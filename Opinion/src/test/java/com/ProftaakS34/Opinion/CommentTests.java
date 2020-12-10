package com.ProftaakS34.Opinion;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {
//    public CommentService commentRepo;
//    public UserService userRepo;
//    public PostService postRepo;
//    UserDAO correctUserDAO;
//    PostDAO correctPostDAO;
//    @BeforeEach
//    public void setUp() {
//        userRepo = new UserService(new MockUserRepo());
//        postRepo = new PostService(new MockPostRepo(), userRepo);
//        commentRepo = new CommentService(new MockCommentRepo(), userRepo, postRepo);
//        correctUserDAO = new UserDAO("username", "password");
//        correctUserDAO.setId(1);
//        userRepo.saveUser(correctUserDAO);
//        correctPostDAO = new PostDAO("politics", correctUserDAO);
//        correctPostDAO.setId(1);
//        postRepo.savePost(correctPostDAO);
//    }
//    @Test
//    public void checksIfCommentHasContent() {
//        CommentDAO commentDAOMissingContent = new CommentDAO();
//        commentDAOMissingContent.setUser(correctUserDAO);
//        commentDAOMissingContent.setPost(correctPostDAO);
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            commentRepo.saveComment(commentDAOMissingContent);
//        });
//    }
//    @Test
//    public void checksIfCommentHasPost() {
//        CommentDAO commentDAOMissingPost = new CommentDAO();
//        commentDAOMissingPost.setUser(correctUserDAO);
//        commentDAOMissingPost.setContent("I agree");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            commentRepo.saveComment(commentDAOMissingPost);
//        });
//    }
//    @Test
//    public void checksIfCommentHasUser(){
//        CommentDAO commentDAOMissingUser = new CommentDAO();
//        commentDAOMissingUser.setPost(correctPostDAO);
//        commentDAOMissingUser.setContent("I agree");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//           commentRepo.saveComment(commentDAOMissingUser);
//        });
//    }
//    @Test
//    public void checksIfPostExists() {
//        CommentDAO commentDAOPostNotInDatabase = new CommentDAO();
//        commentDAOPostNotInDatabase.setContent("I agree");
//        commentDAOPostNotInDatabase.setUser(correctUserDAO);
//        PostDAO fakePostDAO = new PostDAO();
//        commentDAOPostNotInDatabase.setPost(fakePostDAO);
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
//            commentRepo.saveComment(commentDAOPostNotInDatabase);
//        });
//    }
//    @Test
//    public void checksIfUserExists() {
//        CommentDAO commentDAOUserNotInDatabase = new CommentDAO();
//        commentDAOUserNotInDatabase.setPost(correctPostDAO);
//        commentDAOUserNotInDatabase.setContent("I agree");
//        UserDAO fakeUserDAO = new UserDAO();
//        commentDAOUserNotInDatabase.setUser(fakeUserDAO);
//        Assertions.assertThrows(NoSuchElementException.class, () -> {
//            commentRepo.saveComment(commentDAOUserNotInDatabase);
//        });
//    }
//    @Test
//    public void canPlaceComment() {
//        CommentDAO correctCommentDAO = new CommentDAO();
//        correctCommentDAO.setPost(correctPostDAO);
//        correctCommentDAO.setUser(correctUserDAO);
//        correctCommentDAO.setContent("I agree");
//        commentRepo.saveComment(correctCommentDAO);
//        Assertions.assertSame(commentRepo.findAllComments().get(0), correctCommentDAO);
//    }
}
