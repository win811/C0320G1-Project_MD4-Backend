package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private long id;
    @Column(name = "cart_total_price")
    private double totalPrice;
    @Column(name = "cart_status")
    private boolean status;
//    @ManyToOne
//    @JoinColumn(name = "cart_user_id",nullable = false)
//    private User user;
}
