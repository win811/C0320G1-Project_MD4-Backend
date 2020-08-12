package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_code")
    private String code;

    @Column(name = "order_status")
    private boolean status;

    @Column(name = "order_payment_method")
    private String paymentMethod;

    @Column(name = "order_deadline_delivery")
    private Date deadlineDelivery;

    @Column(name = "order_delivery_method")
    private String deliveryMethod;

    @Column(name = "order_payment_status")
    private boolean paymentStatus;


//    @Column(name = "order_buyer_id")
//    private User user;
//
//
//    @Column(name = "order_delivery_address_id")
//    private DeliveryAddresses deliveryAddresses;
//
//    @Column(name = "order_cart_id")
//    private Carts carts;
}
