package md4.bid_project.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class DeliveryAddressDTO {

    private Long id;

    private String fullname;

    private String email;

    private String phoneNumber;

    public DeliveryAddressDTO(Long id, String fullname, String email, String phoneNumber) {
        this.addresses = new ArrayList<>();
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    private List<UserAddressDTO> addresses;


    public void addAddress(DeliveryAddress deliveryAddress) {
        UserAddressDTO addressDTO = new UserAddressDTO();
        addressDTO.setId(deliveryAddress.getId());
        addressDTO.setNation(deliveryAddress.getNation());
        addressDTO.setCity(deliveryAddress.getCity());
        addressDTO.setDistrict(deliveryAddress.getDistrict());
        addressDTO.setWard(deliveryAddress.getWard());
        addressDTO.setStreet(deliveryAddress.getStreet());
        addressDTO.setPhoneNumber(deliveryAddress.getPhoneNumber());
        addressDTO.setIsDefault(deliveryAddress.getIsDefault());
        this.addresses.add(addressDTO);
    }
}
