package md4.bid_project.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_promotion")
public class ProductPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discount_id;

    // tham chiếu đén bảng product
    private Integer product_id;

    private String image;
    private String content;
    private Date saleDate;
    private Date endOfEvent;
    private Integer salePercent;
    private Double price;
    private Integer amount;
    private Boolean flag = true;

    public ProductPromotion() {
    }
}
