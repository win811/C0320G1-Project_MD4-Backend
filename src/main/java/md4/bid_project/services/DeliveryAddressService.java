package md4.bid_project.services;

import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.User;

import java.util.List;

public interface DeliveryAddressService {

    List<DeliveryAddress> findDeliveryAddressByUserId(Long id);
    boolean addDeliveryAddress(DeliveryAddress deliveryAddress);
    boolean updateDeliveryAddress(DeliveryAddress deliveryAddress);
}
