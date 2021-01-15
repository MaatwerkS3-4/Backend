package com.ProftaakS34.Opinion.data;

import com.ProftaakS34.Opinion.domain.model.Category;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.CommentService;
import com.ProftaakS34.Opinion.domain.service.DiscussionService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Component
@Profile("dev")
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final DiscussionService discussionService;
    private final CommentService commentService;
    private Random random;

    public DataLoader(UserService userService, DiscussionService discussionService, CommentService commentService) throws NoSuchAlgorithmException {
        this.userService = userService;
        this.discussionService = discussionService;
        this.commentService = commentService;
        this.random = SecureRandom.getInstanceStrong();
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Data loader called...");

        User user = userService.saveUser("test user 1", "Yes");
        User user2 = userService.saveUser("test user 2", "Yes2");
        User user3 = userService.saveUser("test user 3", "Yes3");
        User user4 = userService.saveUser("test user 4", "Yes4");
        User user5 = userService.saveUser("test user 5", "Yes5");
        List<User> userList = new ArrayList<>();
        Collections.addAll(userList, user, user2, user3, user4, user5);

        List<Category> tags1 = new ArrayList<>( Arrays.asList(
                Category.BUSINESS, Category.EDUCATION
        ));

        List<Category> tags2 = new ArrayList<>( Arrays.asList(
                Category.PHILOSOPHY
        ));

        List<Category> tags3 = new ArrayList<>(Arrays.asList(
                Category.PSYCHOLOGY, Category.HEALTH, Category.SPORT
        ));

        List<Category> tags4 = new ArrayList<>(Arrays.asList(
                Category.HEALTH, Category.SPORT
        ));
        List<List<Category>> categoryList = new ArrayList<>(Arrays.asList(
                tags1, tags2, tags3, tags4
        ));

        List<Discussion> discussions = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            long randomUserId = userList.get(random.nextInt(4 )+ 1).getId();
            Discussion discussion = discussionService.postDiscussion(randomUserId, LoremIpsum.getInstance().getTitle(random.nextInt(10)+3), LoremIpsum.getInstance().getParagraphs(1, 6), categoryList.get(random.nextInt(3)+1));
            discussions.add(discussion);
        }

        for (Discussion discussion : discussions) {
            for(int i = 0; i < random.nextInt(5); i++ ) {
                long randomUserId = userList.get(random.nextInt(4 )+ 1).getId();
                try {
                    Comment comment = commentService.saveComment(discussion.getId(), randomUserId, LoremIpsum.getInstance().getWords(3, 15));
                    for (int j = 0; j < random.nextInt(3); j++) {
                        long newRandomUserId = userList.get(random.nextInt(4) + 1).getId();
                        Comment reply = commentService.saveReply(newRandomUserId, comment.getId(), LoremIpsum.getInstance().getWords(3, 15));

                        for (int k = 0; k < random.nextInt(3); k++) {
                            long newerRandomUserId = userList.get(random.nextInt(4) + 1).getId();
                            commentService.saveReply(newerRandomUserId, reply.getId(), LoremIpsum.getInstance().getWords(3, 15));
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("error occurred while loading lorem ipsum data");
                }
            }
        }
    }

}
