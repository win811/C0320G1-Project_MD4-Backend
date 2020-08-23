package md4.bid_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "auction_statuses")
@Getter
@Setter
@NoArgsConstructor
public class AuctionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="auction_status_id")
    private Long id;

    @Column(name = "auction_status_name")
    private String name;

}
