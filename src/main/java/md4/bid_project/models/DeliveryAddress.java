package md4.bid_project.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "delivery_addresses")
@Data
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Column(name = "delivery_nation")
    private String nation;

    @NotBlank
    @Column(name = "delivery_city")
    private String city;

    @NotBlank
    @Column(name = "delivery_district")
    private String district;

    @NotBlank
    @Column(name = "delivery_ward")
    private String ward;

    @NotBlank
    @Column(name = "delivery_street")
    private String street;

    @Column(name = "delivery_phone_number")
    @Pattern(regexp = "^0[35789]\\d{8}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @Column(name = "delivery_default")
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "delivery_user_id")
    @JsonIgnoreProperties
    private User user;
}
