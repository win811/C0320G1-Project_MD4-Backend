package md4.bid_project.services.impl;

import md4.bid_project.models.CommentLevel2;
import md4.bid_project.repositories.CommentLevel2Repository;
import md4.bid_project.services.CommentLevel2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLevel2ServiceImpl implements CommentLevel2Service {
    //Bach
    @Autowired
    CommentLevel2Repository commentLevel2Repository;

    @Override
    public List<CommentLevel2> getAllCommentLevel2() {
        return commentLevel2Repository.findAll();
    }

    @Override
    public CommentLevel2 getCommentLevel2ById(Long id) {
        return commentLevel2Repository.findById(id).orElse(null);
    }

    @Override
    public void createCommentLevel2(CommentLevel2 commentLevel2) {
        commentLevel2Repository.save(commentLevel2);
    }

    @Override
    public void deleteCommentLevel2(Long id) {
        commentLevel2Repository.deleteById(id);
    }
}
