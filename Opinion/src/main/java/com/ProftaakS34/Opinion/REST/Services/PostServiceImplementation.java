package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.PostRepository;
import com.ProftaakS34.Opinion.Data.Repositories.UserRepository;
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
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post findPostBySubject(String subject) {
        for(Post post : postRepository.findAll()){
            if(post.getSubject().equals(subject)){
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Post> findPostsByPartialSubject(String partialSubject) {
        List<Post> matches = new ArrayList<>();
        for(Post post : postRepository.findAll()){
            if(post.getSubject().contains(partialSubject)){
                matches.add(post);
            }
        }
        return matches;
    }

    @Override
    public List<Post> findAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public Post savePost(Post post) {
        if(post.getSubject() == null || post.getSubject().isEmpty()) throw new IllegalArgumentException("subject is null or empty");
        if(post.getUser() == null) throw new IllegalArgumentException("user is null or incorrect");
        if (userRepository.findUserById(post.getUser().getId()) == null) throw new IllegalArgumentException("user is not in database");
        User user = userRepository.findUserById(post.getUser().getId());
        post.setUser(user);
        return postRepository.save(post);
    }
}
