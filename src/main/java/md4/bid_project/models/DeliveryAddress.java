package md4.bid_project.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "delivery_addresses")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Column(name = "delivery_nation")
    private String nation;

    @NotBlank(message = "Vui lòng nhập tỉnh thành")
    @Column(name = "delivery_city")
    private String city;

    @NotBlank(message = "Vui lòng nhập quận huyện")
    @Column(name = "delivery_district")
    private String district;

    @NotBlank(message = "Vui lòng nhập phường xã")
    @Column(name = "delivery_ward")
    private String ward;

    @NotBlank(message = "Vui lòng nhập địa chỉ giao hàng")
    @Column(name = "delivery_street")
    private String street;

    @NotBlank(message = "Vui lòng nhập số điện thoại giao hàng")
    @Column(name = "delivery_phone_number")
    @Pattern(regexp = "^0[35789]\\d{8}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @Column(name = "delivery_default")
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "delivery_user_id")
    private User user;

    @Column(name = "delivery_status")
    private  Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
