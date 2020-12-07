package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import com.ProftaakS34.Opinion.data.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockPostRepo implements PostRepository {
    public List<PostDAO> postDAOS;
    @Override
    public <S extends PostDAO> S save(S s) {
        postDAOS.add(s);
        return s;
    }

    @Override
    public <S extends PostDAO> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<PostDAO> findById(Long id) {
        for (PostDAO postDAO : postDAOS) {
            if(postDAO.getId()==id) return Optional.of(postDAO);
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<PostDAO> findAll() {
        return postDAOS;
    }

    @Override
    public Iterable<PostDAO> findAllById(Iterable<Long> iterable) {
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
    public void delete(PostDAO postDAO) {

    }

    @Override
    public void deleteAll(Iterable<? extends PostDAO> iterable) {

    }

    @Override
    public void deleteAll() {

    }
    public MockPostRepo() {
        postDAOS = new ArrayList<>();
    }
}
