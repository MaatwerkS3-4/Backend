package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.REST.Services.CommentServiceImplementation;
import com.ProftaakS34.Opinion.REST.Services.PostServiceImplementation;
import com.ProftaakS34.Opinion.REST.Services.UserServiceImplementation;
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
    User  correctUser;
    Post correctPost;
    @BeforeEach
    public void setUp() {
        userRepo = new UserServiceImplementation(new MockUserRepo());
        postRepo = new PostServiceImplementation(new MockPostRepo(), userRepo);
        commentRepo = new CommentServiceImplementation(new MockCommentRepo(), userRepo, postRepo);
        correctUser = new User("username", "password");
        correctUser.setId(1);
        userRepo.saveUser(correctUser);
        correctPost = new Post("politics", correctUser);
        correctPost.setId(1);
        postRepo.savePost(correctPost);
    }
    @Test
    public void checksIfCommentHasContent() {
        Comment commentMissingContent = new Comment();
        commentMissingContent.setUser(correctUser);
        commentMissingContent.setPost(correctPost);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            commentRepo.saveComment(commentMissingContent);
        });
    }
    @Test
    public void checksIfCommentHasPost() {
        Comment commentMissingPost = new Comment();
        commentMissingPost.setUser(correctUser);
        commentMissingPost.setContent("I agree");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            commentRepo.saveComment(commentMissingPost);
        });
    }
    @Test
    public void checksIfCommentHasUser(){
        Comment commentMissingUser = new Comment();
        commentMissingUser.setPost(correctPost);
        commentMissingUser.setContent("I agree");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           commentRepo.saveComment(commentMissingUser);
        });
    }
    @Test
    public void checksIfPostExists() {
        Comment commentPostNotInDatabase = new Comment();
        commentPostNotInDatabase.setContent("I agree");
        commentPostNotInDatabase.setUser(correctUser);
        Post fakePost = new Post();
        commentPostNotInDatabase.setPost(fakePost);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            commentRepo.saveComment(commentPostNotInDatabase);
        });
    }
    @Test
    public void checksIfUserExists() {
        Comment commentUserNotInDatabase = new Comment();
        commentUserNotInDatabase.setPost(correctPost);
        commentUserNotInDatabase.setContent("I agree");
        User fakeUser = new User();
        commentUserNotInDatabase.setUser(fakeUser);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            commentRepo.saveComment(commentUserNotInDatabase);
        });
    }
    @Test
    public void canPlaceComment() {
        Comment correctComment = new Comment();
        correctComment.setPost(correctPost);
        correctComment.setUser(correctUser);
        correctComment.setContent("I agree");
        commentRepo.saveComment(correctComment);
        Assertions.assertSame(commentRepo.findAllComments().get(0), correctComment);
    }
}
