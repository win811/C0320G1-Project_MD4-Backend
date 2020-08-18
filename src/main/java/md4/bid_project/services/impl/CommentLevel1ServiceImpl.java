package md4.bid_project.services.impl;

import md4.bid_project.models.CommentLevel1;
import md4.bid_project.repositories.CommentLevel1Repository;
import md4.bid_project.services.CommentLevel1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLevel1ServiceImpl implements CommentLevel1Service {
    //Bach
    @Autowired
    CommentLevel1Repository commentLevel1Repository;

    @Override
    public List<CommentLevel1> getAllCommentLevel1() {
        return commentLevel1Repository.findAll();
    }

    @Override
    public CommentLevel1 getCommentLevel1ById(Long id) {
        return commentLevel1Repository.findById(id).orElse(null);
    }

    @Override
    public void createCommentLevel1(CommentLevel1 commentLevel1) {
        commentLevel1Repository.save(commentLevel1);
    }

    @Override
    public void deleteCommentLevel1(Long id) {
        commentLevel1Repository.deleteById(id);
    }
}
