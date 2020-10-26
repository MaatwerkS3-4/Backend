package com.ProftaakS34.Opinion.Data.Repositories;

import com.ProftaakS34.Opinion.Data.Entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
