package md4.bid_project.repositories;

import md4.bid_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //CREATE BY ANH DUC
    public User findByEmail(String email);
    //CREATE BY ANH DUC
    public User findByPhoneNumber(String phoneNumber);
}
