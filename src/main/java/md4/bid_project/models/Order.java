package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Boolean status;

    @Column(name = "order_payment_method")
    private String paymentMethod;

    @Column(name = "order_deadline_delivery")
    private LocalDateTime deadlineDelivery;

    @Column(name = "order_delivery_method")
    private String deliveryMethod;

    @Column(name = "order_payment_status")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "order_buyer_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "order_delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @OneToOne
    @JoinColumn(name = "order_cart_id")
    private Cart cart;
}