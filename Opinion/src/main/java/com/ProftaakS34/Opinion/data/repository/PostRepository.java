package com.ProftaakS34.Opinion.data.repository;

import com.ProftaakS34.Opinion.data.dao.PostDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostDAO, Long> {

}
