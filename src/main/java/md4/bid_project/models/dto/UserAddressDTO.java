package md4.bid_project.models.dto;

import lombok.Data;

@Data
public class UserAddressDTO {

    private Long id;

    private String nation;

    private String city;

    private String district;

    private String ward;

    private String street;

    private String phoneNumber;

    private Boolean isDefault;

}
