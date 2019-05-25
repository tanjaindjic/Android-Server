package com.mastercart.service;

import com.mastercart.model.Comment;
import com.mastercart.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllShopComments(Long id){
        return commentRepository.findByForShopId(id);
    }

    public List<Comment> getAllProductComments(Long id){
        return commentRepository.findByForProductId(id);
    }

    public Comment saveComment(Comment c){
        return commentRepository.save(c);
    }
}
