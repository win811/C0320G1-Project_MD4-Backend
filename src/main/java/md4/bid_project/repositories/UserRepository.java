package md4.bid_project.repositories;

import md4.bid_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User, Long>{
}
