package md4.bid_project.repositories;

import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    //    Creator : Cường
    Page<Product> findByOwner_IdAndNameContainingAndApprovementStatus_NameContaining(Long ownerId, String productName,
                                                                                     String approvementStatusName, Pageable pageable);

    List<Product> findByOwner_Id(Long ownerId);
    List<Product> findByApprovementStatus_Id(Long approvementStatusId);

    Optional<Product> findAllById(Long productId);
}
