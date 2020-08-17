package md4.bid_project.services;

import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.dto.DeliveryAddressDTO;

public interface DeliveryAddressService {

    DeliveryAddressDTO findDeliveryAddressByUserId(Long id);
    boolean updateDeliveryAddress(DeliveryAddress deliveryAddress);
}
