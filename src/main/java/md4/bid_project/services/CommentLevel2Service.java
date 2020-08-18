package md4.bid_project.services;

import md4.bid_project.models.CommentLevel2;

import java.util.List;

public interface CommentLevel2Service {
    //Bach
    List<CommentLevel2> getAllCommentLevel2();

    CommentLevel2 getCommentLevel2ById(Long id);

    void createCommentLevel2(CommentLevel2 commentLevel2);

    void deleteCommentLevel2(Long id);
}
