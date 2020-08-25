package md4.bid_project.services;

import md4.bid_project.models.User;

import java.util.List;

public interface UserService {
    User finById(Long id);

    List<User> findAll();
}
