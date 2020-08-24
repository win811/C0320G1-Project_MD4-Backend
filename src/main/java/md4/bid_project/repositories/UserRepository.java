package md4.bid_project.repositories;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


//Tùng

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);
    User findByEmailAndIsLockedIsFalse(String email);
    List<User> findAllByIsLockedIsFalse();
    List<User> findAllByEmailContaining(String email);
//    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
