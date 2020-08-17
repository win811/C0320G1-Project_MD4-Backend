package md4.bid_project.models;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;



@Entity
@Table(name="auctions")
@Data
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "auction_product_id")
    private Product product;

    @ManyToOne
//    @JsonBackReference
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name="auction_status_id")
    private AuctionStatus auctionStatus;

    @Column(name = "auction_close_time")
    private LocalDateTime closeTime;


    @OneToMany(mappedBy = "auction")
//    @JsonManagedReference
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties("auction")
    private List<AuctionRecord> records;




}
