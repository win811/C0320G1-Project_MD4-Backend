package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product_promotions")
@Data
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
    private Date startDate;

    @Column(name = "product_promotion_end_date")
    private Date endDate;

    @Column(name = "product_promotion_percent")
    private Double percent;

    @Column(name = "product_promotion_price")
    private Double price;

    public ProductPromotion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
