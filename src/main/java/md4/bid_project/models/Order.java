package md4.bid_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "order_buyer_id")
    private User buyer;

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
    @JoinColumn(name = "order_delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @OneToOne
    @JoinColumn(name = "order_cart_id")
    private Cart cart;

}
