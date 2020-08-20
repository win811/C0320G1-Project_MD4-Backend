package md4.bid_project.services.Impl;

import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.DeliveryAddressDTO;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public DeliveryAddressDTO findDeliveryAddressByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
            return transferToDTO(user);
        else
            return null;
    }

//    @Override
//    public boolean addDeliveryAddress(DeliveryAddress deliveryAddress) {
//        if (deliveryAddress != null) {
//            deliveryAddressRepository.save(deliveryAddress);
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress != null) {
            deliveryAddress.setNation("Việt Nam");
            deliveryAddress.setIsDefault(true);
            deliveryAddressRepository.save(deliveryAddress);
            return true;
        }
        return false;
    }

    private DeliveryAddressDTO transferToDTO(User user) {
        DeliveryAddressDTO userAddressDTO = new DeliveryAddressDTO(user.getId(), user.getFullName(), user.getEmail(), user.getPhoneNumber());
        for (DeliveryAddress deliveryAddress: user.getDeliveryAddressList()) {
            userAddressDTO.addAddress(deliveryAddress);
        }
        return userAddressDTO;
    }

//    private UserAddressDTO transferToDTO(User user) {
//        userAddressDTO.setId(deliveryAddress.getId());
//        userAddressDTO.setNation(deliveryAddress.getNation());
//        userAddressDTO.setCity(deliveryAddress.getCity());
//        userAddressDTO.setDistrict(deliveryAddress.getDistrict());
//        userAddressDTO.setWard(deliveryAddress.getWard());
//        userAddressDTO.setStreet(deliveryAddress.getStreet());
//        userAddressDTO.setIsDefault(deliveryAddress.getIsDefault());
//        userAddressDTO.setPhoneNumber(deliveryAddress.getPhoneNumber());
//    }

}
