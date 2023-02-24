package com.example.pankaj_gadgets.services;

import com.example.pankaj_gadgets.entity.Comment;
import com.example.pankaj_gadgets.pojo.CommentPojo;

import java.util.*;

public interface CommentService {
    CommentPojo save(CommentPojo commentPojo);

    List<Comment> findAll();
    List<Comment> findCommentById(Integer id);
    List<Comment> findCommentByProductId(Integer id);

    void deleteById(Integer id);
}
