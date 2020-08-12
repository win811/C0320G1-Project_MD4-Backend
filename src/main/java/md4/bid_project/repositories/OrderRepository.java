package md4.bid_project.repositories;

import md4.bid_project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findAllByUser_Id(Long id);
}
