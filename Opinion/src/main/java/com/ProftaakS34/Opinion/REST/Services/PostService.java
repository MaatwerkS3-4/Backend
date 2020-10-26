package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.Post;

import java.util.List;

public interface PostService {

    Post findPostById(Long id);

    Post findPostBySubject(String subject);

    List<Post> findPostsByPartialSubject(String partialSubject);

    List<Post> findAllPosts();

    Post savePost(Post post);
}
