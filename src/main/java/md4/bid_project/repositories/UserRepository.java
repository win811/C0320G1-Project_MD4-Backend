package md4.bid_project.repositories;

import md4.bid_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmailAndIsLockedIsFalse(String email);
    List<User> findAllByIsLockedIsFalse();
    List<User> findAllByEmailContaining(String email);
}
