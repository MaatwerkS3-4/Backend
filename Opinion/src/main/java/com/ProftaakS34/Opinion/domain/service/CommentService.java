package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;
import com.ProftaakS34.Opinion.data.repository.DiscussionRepository;
import com.ProftaakS34.Opinion.domain.mapper.CommentMapper;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.Comment;
import com.ProftaakS34.Opinion.domain.model.Discussion;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final DiscussionRepository discussionRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, UserMapper userMapper, CommentMapper commentMapper, DiscussionRepository discussionRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.discussionRepository = discussionRepository;
    }

    public List<Comment> findCommentsByUserId(long id){
        List<Comment> comments = new ArrayList<>();
        for(CommentDAO c : commentRepository.findCommentDAOSByPosterId(id)){
            comments.add(commentMapper.toModel(c));
        }

        return comments;
    }

    public Comment saveComment(long discussionId, long posterId, String content) {
        //Check content and get poster
        User poster = checkCommentContent(posterId, content);

        //Find discussion
        Optional<DiscussionDAO> promise = discussionRepository.findById(discussionId);
        if(promise.isEmpty()) throw new IllegalArgumentException("Discussion not found");
        DiscussionDAO discussion = promise.get();

        //Save comment
        Comment model = new Comment(content, poster);
        CommentDAO comment = commentRepository.save(commentMapper.toDAO(model));

        //Add comment to discussion
        discussion.getComments().add(comment);
        discussionRepository.save(discussion);

        return commentMapper.toModel(comment);
    }

    public Comment saveReply(long discussionId, long posterId, long commentId, String content){
        //Check content and get poster
        User poster = checkCommentContent(posterId, content);

        //Get parent comment
        CommentDAO parent = commentRepository.findById(commentId).get();

        //Save reply
        Comment model = new Comment(content, poster);
        CommentDAO comment = commentRepository.save((commentMapper.toDAO(model)));

        parent.getReplies().add(comment);
        commentRepository.save(parent);

        return commentMapper.toModel(comment);
    }

    private User checkCommentContent(long posterId, String content){
        //Check content
        if(content == null || content.isEmpty() || content.isBlank())
            throw new IllegalArgumentException("Invalid content");

        //Find user
        User poster = userService.findUserById(posterId);
        if(poster == null) throw new IllegalArgumentException("User not found");

        return poster;
    }


    public int getNumberOfParticipantsByDiscussion(Discussion discussion) {

        List<User> uniqueUserDAOS = new ArrayList<>();
        for (Comment comment: discussion.getComments()) {
            if (!uniqueUserDAOS.contains(comment.getPoster())) uniqueUserDAOS.add(comment.getPoster());
        }

        return uniqueUserDAOS.size();
    }

    public void upvoteComment(long commentId, long userId) {
        CommentDAO comment = commentRepository.findById(commentId).get();
        UserDAO user = userMapper.toDAO(userService.findUserById(userId));
        comment.getCommentUpvoters().add(user);
        commentRepository.save(comment);
    }
}
