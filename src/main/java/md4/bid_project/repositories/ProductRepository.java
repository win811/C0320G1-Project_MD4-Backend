package md4.bid_project.repositories;

import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByOwner_Id(Long ownerId);

    List<Product> findByApprovementStatus_Id(Long approvementStatusId);

    // Created by: Toàn
    Page<Product> findByOwner_IdAndApprovementStatus_Id(Long userId, Long productApprovementStatusId,
            Pageable pageable);

    // Creator : Cường
    Page<Product> findByOwner_IdAndNameContainingAndApprovementStatus_NameContaining(Long ownerId, String productName,
            String approvementStatusName, Pageable pageable);

}
