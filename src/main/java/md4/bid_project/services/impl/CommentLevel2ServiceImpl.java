package md4.bid_project.services.impl;

import md4.bid_project.repositories.CommentLevel2Repository;
import md4.bid_project.services.CommentLevel2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentLevel2ServiceImpl implements CommentLevel2Service {
    @Autowired
    CommentLevel2Repository commentLevel2Repository;
}
