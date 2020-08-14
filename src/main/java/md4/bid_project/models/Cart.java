package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "cart_total_price")
    private Double totalPrice;

    @Column(name = "cart_status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "cart_user_id")
    private User user;

}
