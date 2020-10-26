package com.ProftaakS34.Opinion.Data.Repositories;

import com.ProftaakS34.Opinion.Data.Entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
