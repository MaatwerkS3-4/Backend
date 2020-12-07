package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUserRepo implements UserRepository {
    public List<UserDAO> userDAOS;
    @Override
    public <S extends UserDAO> S save(S s) {
        userDAOS.add(s);
        return s;
    }

    @Override
    public <S extends UserDAO> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<UserDAO> findById(Long id) {
        for (UserDAO userDAO : userDAOS) {
            if(userDAO.getId() == id) return Optional.of(userDAO);
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserDAO> findAll() {
        return userDAOS;
    }

    @Override
    public Iterable<UserDAO> findAllById(Iterable<Long> iterable) {
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
    public void delete(UserDAO userDAO) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserDAO> iterable) {

    }

    @Override
    public void deleteAll() {

    }
    public MockUserRepo(){
        userDAOS = new ArrayList<>();
    }
}
