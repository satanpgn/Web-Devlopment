package com.example.pankaj_gadgets.repo;

import com.example.pankaj_gadgets.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    @Query(value = "SELECT * FROM comments where user_id=?1", nativeQuery = true)
    List<Comment> findCommentById(Integer id);

    @Query(value = "SELECT * FROM comments where product_id=?1", nativeQuery = true)
    List<Comment> findCommentByProductId(Integer id);
}
