package md4.bid_project.repositories;

import md4.bid_project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByOwner_Id(Long ownerId);
    List<Product> findByApprovementStatus_Id(Long approvementStatusId);
}
