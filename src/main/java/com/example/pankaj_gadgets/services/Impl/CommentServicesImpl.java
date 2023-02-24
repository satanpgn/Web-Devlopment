package com.example.pankaj_gadgets.services.Impl;

import com.example.pankaj_gadgets.entity.Comment;
import com.example.pankaj_gadgets.entity.Product;
import com.example.pankaj_gadgets.pojo.CommentPojo;
import com.example.pankaj_gadgets.repo.CommentRepo;
import com.example.pankaj_gadgets.repo.ProductRepo;
import com.example.pankaj_gadgets.repo.UserRepo;
import com.example.pankaj_gadgets.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommentServicesImpl implements CommentService {
    private  final CommentRepo commentRepo;
    private  final UserRepo userRepo;
    private  final ProductRepo productRepo;
    @Override
    public CommentPojo save(CommentPojo commentPojo) {
        Comment comment= new Comment();
        if(commentPojo.getId()!=null){
            comment.setId(comment.getId());
        }
        comment.setComment(commentPojo.getComment());
        comment.setProduct_id(productRepo.findById(commentPojo.getProduct_id()).orElseThrow());
        comment.setUser_id(userRepo.findById(commentPojo.getUser_id()).orElseThrow());

        commentRepo.save(comment);
        return new CommentPojo(comment);
    }

    @Override
    public List<Comment> findAll() {
        return findAllInList(commentRepo.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<Comment> findCommentById(Integer id) {
        return findAllInList(commentRepo.findCommentById(id));

    }
    @Override
    public List<Comment> findCommentByProductId(Integer id) {
        return findAllInList(commentRepo.findCommentByProductId(id));

    }

    public List<Comment> findAllInList(List<Comment> list){
        Stream<Comment> allJobsWithImage = list.stream().map(comment ->
                Comment.builder()
                        .id(comment.getId())
                        .comment(comment.getComment())
                        .user_id(comment.getUser_id())
                        .product_id(comment.getProduct_id())
                        .build());
        list = allJobsWithImage.toList();
        return list;
    }
}
