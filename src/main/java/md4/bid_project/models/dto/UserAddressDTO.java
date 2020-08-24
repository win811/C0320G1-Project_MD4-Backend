package md4.bid_project.models.dto;

import lombok.Data;

public class UserAddressDTO {

    private Long id;

    private String nation;

    private String city;

    private String district;

    private String ward;

    private String street;

    private String phoneNumber;

    private Boolean isDefault;

    public UserAddressDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserAddressDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNation() {
        return nation;
    }

    public UserAddressDTO setNation(String nation) {
        this.nation = nation;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserAddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public UserAddressDTO setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public UserAddressDTO setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public UserAddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserAddressDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public UserAddressDTO setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }
}
