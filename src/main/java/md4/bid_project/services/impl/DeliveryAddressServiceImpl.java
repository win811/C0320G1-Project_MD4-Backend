package md4.bid_project.services.impl;

import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.services.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

}
