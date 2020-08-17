package md4.bid_project.repositories;
// creator: Hoai Ngan team C
import md4.bid_project.models.AuctionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRecordRepository extends JpaRepository<AuctionRecord, Long> {
    @Query(value = "SELECT * FROM auction_records WHERE record_auction_id = ?1 ORDER BY record_bid_price DESC LIMIT 3", nativeQuery = true)
    List<AuctionRecord> getLatestAuctionRecord(@Param("auctionId") Long auctionId);

    @Query(value = "SELECT * FROM bid_project.auction_records HAVING record_auction_id = ?1 ORDER BY record_bid_price DESC LIMIT 1", nativeQuery = true)
    AuctionRecord getRecordHavingBestPrice(@Param("auctionId") Long auctionId);




}
