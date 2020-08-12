package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "favorite products")
@Data
public class FavoriteProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite product id")
    private Long FavoriteProductId;

    @Column(name = "user id")
    private Integer UserId;
    @Column(name = "product id")
    private Integer ProductId;
    @Column(name = "favorite product status")
    private String FavoriteProductStatus;
}
