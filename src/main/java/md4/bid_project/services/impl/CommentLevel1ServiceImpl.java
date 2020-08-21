package md4.bid_project.services.impl;

import md4.bid_project.models.CommentLevel1;
import md4.bid_project.models.CommentLevel2;
import md4.bid_project.models.dto.ProductCommentDTO;
import md4.bid_project.repositories.CommentLevel1Repository;
import md4.bid_project.services.CommentLevel1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<ProductCommentDTO> getAllCommentLevel1ByProductId(Long productId) {
        List<ProductCommentDTO> productCommentDTO = new ArrayList<>();
        List<CommentLevel1> commentList = commentLevel1Repository.findAllByProduct_Id(productId);
        for (CommentLevel1 commentLevel1: commentList) {
            productCommentDTO.add(transferLevel1(commentLevel1));
        }
        return productCommentDTO;
    }

    private ProductCommentDTO transferLevel1(CommentLevel1 commentLevel1) {
        // comment 2
        List<ProductCommentDTO> productComment2 = new ArrayList<>();
        for (CommentLevel2 commentLevel2: commentLevel1.getCommentLevel2s()) {
            productComment2.add(transferLevel2(commentLevel2));
        }
        // add comment1
        ProductCommentDTO productComment1 = new ProductCommentDTO();
        productComment1.setId(commentLevel1.getId())
                .setContent(commentLevel1.getContent())
                .setUser(commentLevel1.getUser())
                .setCommentLevel2List(productComment2);
        return productComment1;
    }

    private ProductCommentDTO transferLevel2(CommentLevel2 commentLevel2) {
        ProductCommentDTO productComment2 = new ProductCommentDTO();
        productComment2.setId(commentLevel2.getId())
                .setContent(commentLevel2.getContent())
                .setUser(commentLevel2.getUser());
        return productComment2;
    }
}
