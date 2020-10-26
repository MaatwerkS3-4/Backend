package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;

    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post findPostBySubject(String subject) {

        return null;
    }

    @Override
    public Post findPostByPartialSubject(String partialSubject) {
        return null;
    }

    @Override
    public List<Post> findAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
