package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

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
    private String status;

    @Column(name = "order_payment_method")
    private String paymentMethod;

    @Column(name = "order_deadline_delivery")
    private Date deadlineDelivery;

    @Column(name = "order_delivery_method")
    private String deliveryMethod;

    @Column(name = "order_payment_status")
    private boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(name = "order_buyer_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="delivery_id")
    @Column(name = "order_delivery_address_id")
    private DeliveryAddresses deliveryAddresses;

    @OneToOne(mappedBy = "order")
    @Column(name = "order_cart_id")
    private Carts carts;
}
