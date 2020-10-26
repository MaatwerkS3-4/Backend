package com.ProftaakS34.Opinion.Data.Repositories;

import com.ProftaakS34.Opinion.Data.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
