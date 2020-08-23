package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// creator: Hoai Ngan team C
@Entity
@Table(name="auctions")
@Getter
@Setter
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "auction_product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="auction_status_id")
    private AuctionStatus auctionStatus;

    @Column(name = "auction_close_time")
    private LocalDateTime closeTime;

    @OneToMany(mappedBy = "auction")
    @JsonIgnoreProperties("auction")
    private List<AuctionRecord> records;

}
