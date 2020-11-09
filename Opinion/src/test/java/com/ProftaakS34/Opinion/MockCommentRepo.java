package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import com.ProftaakS34.Opinion.Data.Repositories.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockCommentRepo implements CommentRepository {
    List<Comment> comments;
    @Override
    public <S extends Comment> S save(S s) {
        comments.add(s);
        return s;
    }

    @Override
    public <S extends Comment> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Comment> findAll() {
        return comments;
    }

    @Override
    public Iterable<Comment> findAllById(Iterable<Long> iterable) {
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
    public void delete(Comment comment) {

    }

    @Override
    public void deleteAll(Iterable<? extends Comment> iterable) {

    }

    @Override
    public void deleteAll() {

    }
    public MockCommentRepo() {
        comments = new ArrayList<>();
    }
}
