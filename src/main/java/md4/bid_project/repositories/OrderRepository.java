package md4.bid_project.repositories;
import md4.bid_project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//creator: Đặng Hồng Quân team C
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findAllByBuyer_IdAndStatusTrue(Long id);
}