package md4.bid_project.repositories;

import md4.bid_project.models.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

//    List<DeliveryAddress> findAllByUser(User user);
}
