package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "delivery_addresses")
@Getter
@Setter
@NoArgsConstructor
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
    @JsonIgnoreProperties
    private User user;

    @Column(name = "delivery_status")
    private  Boolean status;

}
