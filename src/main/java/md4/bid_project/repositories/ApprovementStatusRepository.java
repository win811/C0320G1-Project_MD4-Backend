package md4.bid_project.repositories;

import md4.bid_project.models.ApprovementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ApprovementStatusRepository extends JpaRepository<ApprovementStatus,Long> {
}
