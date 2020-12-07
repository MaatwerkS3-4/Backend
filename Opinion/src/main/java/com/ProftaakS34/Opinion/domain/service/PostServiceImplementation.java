package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    private final UserServiceImplementation userRepository;

    public PostServiceImplementation(PostRepository postRepository, UserServiceImplementation userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDAO findPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public PostDAO findPostBySubject(String subject) {
        for(PostDAO postDAO : postRepository.findAll()){
            if(postDAO.getSubject().equals(subject)){
                return postDAO;
            }
        }
        return null;
    }

    @Override
    public List<PostDAO> findPostsByPartialSubject(String partialSubject) {
        List<PostDAO> matches = new ArrayList<>();
        for(PostDAO postDAO : postRepository.findAll()){
            if(postDAO.getSubject().contains(partialSubject)){
                matches.add(postDAO);
            }
        }
        return matches;
    }

    @Override
    public List<PostDAO> findAllPosts() {
        return (List<PostDAO>) postRepository.findAll();
    }

    @Override
    public PostDAO savePost(PostDAO postDAO) {
        if(postDAO.getSubject() == null || postDAO.getSubject().isEmpty()) throw new IllegalArgumentException("subject is null or empty");
        if(postDAO.getUser() == null) throw new IllegalArgumentException("user is null or incorrect");
        if (userRepository.findUserById(postDAO.getUser().getId()) == null) throw new IllegalArgumentException("user is not in database");
        UserDAO userDAO = userRepository.findUserById(postDAO.getUser().getId());
        postDAO.setUser(userDAO);
        return postRepository.save(postDAO);
    }
}
