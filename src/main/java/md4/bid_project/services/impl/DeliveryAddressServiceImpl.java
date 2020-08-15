package md4.bid_project.services.impl;

import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.services.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Override
    public List<DeliveryAddress> findDeliveryAddressByUserId(Long id) {
        return deliveryAddressRepository.findAllByUser_Id(id);
    }

    @Override
    public boolean addDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress != null) {
            deliveryAddressRepository.save(deliveryAddress);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress != null) {
            deliveryAddressRepository.save(deliveryAddress);
            return true;
        }
        return false;
    }
}
