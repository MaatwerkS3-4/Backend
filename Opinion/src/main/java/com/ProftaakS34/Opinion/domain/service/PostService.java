package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.PostDAO;

import java.util.List;

public interface PostService {

    PostDAO findPostById(Long id);

    PostDAO findPostBySubject(String subject);

    List<PostDAO> findPostsByPartialSubject(String partialSubject);

    List<PostDAO> findAllPosts();

    PostDAO savePost(PostDAO postDAO);
}
