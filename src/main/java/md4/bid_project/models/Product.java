package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_initial_price")
    private Double initialPrice;

    @Column(name = "product_increase_amount")
    private Double increaseAmount;

    @Column(name = "product_register_date")
    private LocalDateTime registerDate;

    @Column(name = "product_start_date")
    private LocalDateTime startDate;

    @Column(name = "product_end_date")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "product_approvement_status_id")
    private ApprovementStatus approvementStatus;

    @Column(name = "product_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "product_owner_id")
    private User owner;

}