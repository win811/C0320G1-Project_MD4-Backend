package md4.bid_project.repositories;
import md4.bid_project.models.Cart;
import md4.bid_project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    public Order findAllByCart(Cart cart);

    public Order findByCart(Cart cart);

}