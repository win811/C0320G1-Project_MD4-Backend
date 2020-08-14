package md4.bid_project.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "auction_statuses")
@Data
public class AuctionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auction_status_id")
    private Long id;

    @Column(name = "auction_status_name")
    private String name;

//    @OneToMany(mappedBy = "auctionStatus")
//    @JsonManagedReference
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonIgnoreProperties("auctionStatus")
//    private List<Auction> auctions;

}
