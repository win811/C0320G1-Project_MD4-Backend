package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "delivery_addresses")
@Data
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private long id;

    @Column(name = "delivery_nation")
    private String nation;

    @Column(name = "delivery_city")
    private String city;

    @Column(name = "delivery_district")
    private String district;

    @Column(name = "delivery_ward")
    private String ward;

    @Column(name = "delivery_street")
    private String street;

    @Column(name = "delivery_phone_number")
    private String phoneNumber;

    @Column(name = "delivery_default")
    private boolean isDefault;

//    @ManyToOne
//    @JoinColumn(name = "delivery_user_id")
//    private User user;
}
