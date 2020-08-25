package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long id;

    @Column(name = "product_image_link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

