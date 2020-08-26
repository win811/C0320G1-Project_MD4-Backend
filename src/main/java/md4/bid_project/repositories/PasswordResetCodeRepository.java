package md4.bid_project.repositories;

import md4.bid_project.models.PasswordResetCode;
import md4.bid_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {
    public void deleteAllByUser(User user);

    public void deleteAll();

}
