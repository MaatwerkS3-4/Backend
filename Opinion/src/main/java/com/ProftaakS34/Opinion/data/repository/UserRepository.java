package com.ProftaakS34.Opinion.data.repository;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Long> {
}
