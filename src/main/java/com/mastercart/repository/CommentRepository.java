package com.mastercart.repository;

import com.mastercart.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByForShopId(Long id);
    List<Comment> findByForProductId(Long id);
}
