package md4.bid_project.models.dto;

import md4.bid_project.models.DeliveryAddress;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressDTO {

    private Long id;

    private String fullname;

    private String email;

    private String phoneNumber;

    private List<UserAddressDTO> addresses;

    public DeliveryAddressDTO() {
        this.addresses = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public DeliveryAddressDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public DeliveryAddressDTO setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DeliveryAddressDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DeliveryAddressDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<UserAddressDTO> getAddresses() {
        return addresses;
    }

    public DeliveryAddressDTO setAddresses(List<DeliveryAddress> addressList) {
        for (DeliveryAddress deliveryAddress : addressList) {
            this.addresses.add(addAddress(deliveryAddress));
        }
//        this.addresses = addresses;
        return this;
    }

    private UserAddressDTO addAddress(DeliveryAddress deliveryAddress) {
        UserAddressDTO addressDTO = new UserAddressDTO();
        addressDTO.setId(deliveryAddress.getId())
                .setNation(deliveryAddress.getNation())
                .setCity(deliveryAddress.getCity())
                .setDistrict(deliveryAddress.getDistrict())
                .setWard(deliveryAddress.getWard())
                .setStreet(deliveryAddress.getStreet())
                .setPhoneNumber(deliveryAddress.getPhoneNumber())
                .setIsDefault(deliveryAddress.getIsDefault());
        return addressDTO;
    }
}
