package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "auction_statuses")
public class AuctionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auction_status_id")
    private long id;

    @Column(name = "auction_status_name")
    private String name;

//    @OneToMany(mappedBy = "auction_status")
//    private Set<Auction> auctions;
}
