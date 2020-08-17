package md4.bid_project.repositories;

import md4.bid_project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByOwner_Id(Long ownerId);
    List<Product> findByApprovementStatus_Id(Long approvementStatusId);

    Optional<Product> findAllById(Long productId);
}
