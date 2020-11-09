package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import com.ProftaakS34.Opinion.Data.Repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockPostRepo implements PostRepository {
    public List<Post> posts;
    @Override
    public <S extends Post> S save(S s) {
        posts.add(s);
        return s;
    }

    @Override
    public <S extends Post> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Post> findAll() {
        return posts;
    }

    @Override
    public Iterable<Post> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Post post) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> iterable) {

    }

    @Override
    public void deleteAll() {

    }
    public MockPostRepo() {
        posts = new ArrayList<>();
    }
}
