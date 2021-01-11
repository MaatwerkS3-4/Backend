package com.ProftaakS34.Opinion.Mocks;

import com.ProftaakS34.Opinion.data.dao.DiscussionDAO;
import com.ProftaakS34.Opinion.data.repository.DiscussionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockDiscussionRepo implements DiscussionRepository {
    public List<DiscussionDAO> postDAOS;
    @Override
    public <S extends DiscussionDAO> S save(S s) {
        postDAOS.add(s);
        return s;
    }

    @Override
    public <S extends DiscussionDAO> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<DiscussionDAO> findById(Long id) {
        for (DiscussionDAO postDAO : postDAOS) {
            if(postDAO.getId()==id) return Optional.of(postDAO);
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<DiscussionDAO> findAll() {
        return postDAOS;
    }

    @Override
    public Iterable<DiscussionDAO> findAllById(Iterable<Long> iterable) {
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
    public void delete(DiscussionDAO postDAO) {

    }

    @Override
    public void deleteAll(Iterable<? extends DiscussionDAO> iterable) {

    }

    @Override
    public void deleteAll() {

    }
    public MockDiscussionRepo() {
        postDAOS = new ArrayList<>();
    }
}
