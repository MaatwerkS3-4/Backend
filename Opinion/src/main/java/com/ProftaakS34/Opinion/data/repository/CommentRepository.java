package com.ProftaakS34.Opinion.data.repository;

import com.ProftaakS34.Opinion.data.dao.CommentDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentDAO, Long> {
    List<CommentDAO> findCommentDAOSByPosterId(long id);
}
