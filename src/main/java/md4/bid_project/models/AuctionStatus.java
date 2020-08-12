package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
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

}
