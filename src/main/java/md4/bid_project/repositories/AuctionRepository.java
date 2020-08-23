package md4.bid_project.repositories;
// creator: Hoai Ngan team C
import md4.bid_project.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {

    //Creator: BHung
    List<Auction> findAllByAuctionStatus_Id(Long id);

    @Query(value = "select * from auctions join auction_statuses on auctions.auction_status_id=auction_statuses.auction_status_id join auction_records on auctions.auction_id=auction_records.record_auction_id where auction_statuses.auction_status_id=3 and record_bid_is_winner=true order by record_bid_price desc limit 5", nativeQuery = true)
    List<Auction> findTop5Auctions();

    @Query(value = "select * from auctions join products on auctions.auction_product_id=products.product_id join categories on products.product_category_id = categories.category_id where auction_status_id = ?1 and category_name like %?2%",nativeQuery = true)
    List<Auction> findAuctionsByStatusIdAndCategoryName(Long id,String name);
}
