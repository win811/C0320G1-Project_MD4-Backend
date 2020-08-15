package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties(value = "productImages")
    private Product product;
}
