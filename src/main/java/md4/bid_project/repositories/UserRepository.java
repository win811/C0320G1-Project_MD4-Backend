package md4.bid_project.repositories;

import md4.bid_project.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//Tùng
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByEmailAndIsLockedIsFalse(String email);
    List<User> findAllByIsLockedIsFalse();
    List<User> findAllByEmailContaining(String email);
    //CREATE BY ANH DUC
    public User findByEmail(String email);
    //CREATE BY ANH DUC
    public User findByPhoneNumber(String phoneNumber);

//    Optional<User> findByPhoneNumber(String phoneNumber);

    //Hoàng Long method
    Page<User> findAllByAndIsLockedIsFalseAndFullNameContaining(Pageable pageable, String fullName);
}
