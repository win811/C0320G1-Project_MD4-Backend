package md4.bid_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_promotions")
@Getter
@Setter
@NoArgsConstructor
public class ProductPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_promotion_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "product_promotion_content")
    private String content;

    @Column(name = "product_promotion_start_date")
    private LocalDateTime startDate;

    @Column(name = "product_promotion_end_date")
    private LocalDateTime endDate;

    @Column(name = "product_promotion_percent")
    private Double percent;

    @Column(name = "product_promotion_price")
    private Double price;

}
