package md4.bid_project.services.impl;

import md4.bid_project.models.User;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User finById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
