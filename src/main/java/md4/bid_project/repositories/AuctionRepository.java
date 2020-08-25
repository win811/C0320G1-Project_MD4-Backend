package md4.bid_project.repositories;
// creator: Hoai Ngan team C
import md4.bid_project.models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction,Long> {

    // Bach
    Auction getAuctionByProduct_Id(Long productId);

    //Creator: BHung
    List<Auction> findAllByAuctionStatus_Id(Long id);

    //Creator: BHung
    @Query(value = "select * from auctions join auction_statuses on auctions.auction_status_id=auction_statuses.auction_status_id join auction_records on auctions.auction_id=auction_records.record_auction_id where auction_statuses.auction_status_id=3 and record_bid_is_winner=true order by record_bid_price desc limit 5", nativeQuery = true)
    List<Auction> findTop5Auctions();

    //Creator: BHung
    @Query(value = "select * from auctions join products on auctions.auction_product_id=products.product_id join categories on products.product_category_id = categories.category_id where auction_status_id = ?1 and category_name like %?2%",nativeQuery = true)
    List<Auction> findAuctionsByStatusIdAndCategoryName(Long id,String name);

    //Creator: BHung
    @Query(value = "select * from auctions as a join (select auction_records.record_id,auction_records.record_auction_id,record_bid_is_winner,max(auction_records.record_bid_price) as record_bid_price from auction_records group by record_auction_id) as b on a.auction_id = b.record_auction_id join products as c on a.auction_product_id = c.product_id join categories as d on c.product_category_id = d.category_id where b.record_bid_price between ?3 and ?4 and c.product_name like %?1% and d.category_name like %?2% and a.auction_status_id=2",nativeQuery = true)
    List<Auction> findAllAuctionsByProductNameAndCategoryNameAndPriceRange(String productName,String categoryName,long from, long end);

    //Creator: BHung
    @Query(value = "select * from auctions as a join (select auction_records.record_id,auction_records.record_auction_id,record_bid_is_winner,max(auction_records.record_bid_price) as record_bid_price from auction_records group by record_auction_id) as b on a.auction_id = b.record_auction_id join products as c on a.auction_product_id = c.product_id join categories as d on c.product_category_id = d.category_id and c.product_name like %?1% and d.category_name like %?2% and a.auction_status_id=2",nativeQuery = true)
    List<Auction> findAllAuctionsByProductNameAndCategoryName(String productName, String CategoryName);

    //Creator: BHung
    @Query(value = "select * from auctions as a join (select auction_records.record_id,auction_records.record_auction_id,record_bid_is_winner,max(auction_records.record_bid_price) as record_bid_price from auction_records group by record_auction_id) as b on a.auction_id = b.record_auction_id join products as c on a.auction_product_id = c.product_id join categories as d on c.product_category_id = d.category_id where b.record_bid_price >= ?3 and c.product_name like %?1% and d.category_name like %?2% and a.auction_status_id=2",nativeQuery = true)
    List<Auction> findAllAuctionsByProductNameAndCategoryNameAndPriceMoreThan(String productName,String categoryName, Long price);
}
