package md4.bid_project.services;

import md4.bid_project.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//Tùng
public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void removeById(Long id);

    Page<User> findAllByRateContaining(String rate, Pageable pageable);
}
