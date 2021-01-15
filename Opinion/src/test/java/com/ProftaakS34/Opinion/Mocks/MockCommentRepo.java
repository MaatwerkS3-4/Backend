package com.ProftaakS34.Opinion.Mocks;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import com.ProftaakS34.Opinion.data.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MockCommentRepo implements CommentRepository {
    List<CommentDAO> commentDAOS;

    @Override
    public <S extends CommentDAO> S save(S s) {
        commentDAOS.add(s);
        return s;
    }

    @Override
    public <S extends CommentDAO> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CommentDAO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<CommentDAO> findAll() {
        return commentDAOS;
    }

    @Override
    public Iterable<CommentDAO> findAllById(Iterable<Long> iterable) {
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
    public void delete(CommentDAO commentDAO) {

    }

    @Override
    public void deleteAll(Iterable<? extends CommentDAO> iterable) {

    }

    @Override
    public void deleteAll() {

    }
    public MockCommentRepo() {
        commentDAOS = new ArrayList<>();
    }

    @Override
    public List<CommentDAO> findCommentDAOSByPosterId(long id) {
        return commentDAOS.stream().filter(c -> c.getPoster().getId() == id).collect(Collectors.toList());
    }
}
