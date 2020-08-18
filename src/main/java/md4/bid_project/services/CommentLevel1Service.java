package md4.bid_project.services;

import md4.bid_project.models.CommentLevel1;

import java.util.List;

public interface CommentLevel1Service {
    //Bach
    List<CommentLevel1> getAllCommentLevel1();

    CommentLevel1 getCommentLevel1ById(Long id);

    void createCommentLevel1 (CommentLevel1 commentLevel1);

    void deleteCommentLevel1 (Long id);
}
