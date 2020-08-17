package md4.bid_project.controllers;

import md4.bid_project.models.CommentLevel1;
import md4.bid_project.models.CommentLevel2;
import md4.bid_project.services.CommentLevel1Service;
import md4.bid_project.services.CommentLevel2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    CommentLevel1Service commentLevel1Service;

    @Autowired
    CommentLevel2Service commentLevel2Service;

    @GetMapping("/comment-level1")
    public List<CommentLevel1> getAllCommentLevel1() {
        return commentLevel1Service.getAllCommentLevel1();
    }

    @GetMapping("/comment-level1/{id}")
    public ResponseEntity<CommentLevel1> getCommentLevel1ById(@PathVariable Long id) {
        CommentLevel1 commentLevel1 = commentLevel1Service.getCommentLevel1ById(id);
        if (commentLevel1 == null) {
            return new ResponseEntity<CommentLevel1>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CommentLevel1>(commentLevel1, HttpStatus.OK);
    }

    @PostMapping("/comment-level1")
    public ResponseEntity<Void> createCommentLevel1(@RequestBody CommentLevel1 commentLevel1) {
        commentLevel1Service.createCommentLevel1(commentLevel1);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/comment-level1/{id}")
    public ResponseEntity<CommentLevel1> deleteCommentLevel1(@PathVariable Long id) {
        CommentLevel1 commentLevel1 = commentLevel1Service.getCommentLevel1ById(id);
        if (commentLevel1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentLevel1Service.deleteCommentLevel1(id);
        return new ResponseEntity<CommentLevel1>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/comment-level2")
    public List<CommentLevel2> getAllCommentLevel2() {
        return commentLevel2Service.getAllCommentLevel2();
    }

    @GetMapping("/comment-level2/{id}")
    public ResponseEntity<CommentLevel2> getCommentLevel2ById(@PathVariable Long id) {
        CommentLevel2 commentLevel2 = commentLevel2Service.getCommentLevel2ById(id);
        if (commentLevel2 == null) {
            return new ResponseEntity<CommentLevel2>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CommentLevel2>(commentLevel2, HttpStatus.OK);
    }

    @PostMapping("/comment-level2")
    public ResponseEntity<Void> createCommentLevel2(@RequestBody CommentLevel2 commentLevel2) {
        commentLevel2Service.createCommentLevel2(commentLevel2);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/comment-level2/{id}")
    public ResponseEntity<CommentLevel2> deleteCommentLevel2(@PathVariable Long id) {
        CommentLevel2 commentLevel2 = commentLevel2Service.getCommentLevel2ById(id);
        if (commentLevel2 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentLevel2Service.deleteCommentLevel2(id);
        return new ResponseEntity<CommentLevel2>(HttpStatus.NO_CONTENT);
    }
}
