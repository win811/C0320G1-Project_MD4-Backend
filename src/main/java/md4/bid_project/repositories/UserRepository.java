package md4.bid_project.repositories;

import md4.bid_project.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findAllByEmailContaining(String email);

    //Hoàng Long method
    Page<User> findAllByAndIsLockedIsFalseAndFullnameContaining(String fullName, Pageable pageable);
}
