package md4.bid_project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_user_id")
    private User user;

    @Column(name = "cart_total_price")
    private Double totalPrice;

    @Column(name = "cart_status")
    private Boolean status;

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetails;
}
