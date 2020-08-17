package md4.bid_project.repositories;

import md4.bid_project.models.AuctionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRecordRepository extends JpaRepository<AuctionRecord, Long> {
    @Query(value = "SELECT * FROM auction_records WHERE record_auction_id = ?1 ORDER BY record_bid_price DESC LIMIT 3", nativeQuery = true)
    List<AuctionRecord> getLatestAuctionRecord(@Param("auctionId") Long auctionId);

    @Query(value = "SELECT * FROM bid_project.auction_records HAVING record_auction_id = ?1 ORDER BY record_bid_price DESC LIMIT 1", nativeQuery = true)
    AuctionRecord getRecordHavingBestPrice(@Param("auctionId") Long auctionId);





    //    Cường (search tất cả)
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContaining(Long bidderId, String productName, Pageable pageable);
    //    Cường (search đang đấu giá)
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContainingAndAuction_AuctionStatus_Name(Long bidderId, String productName,
                                                                                            String auctionStatusName,
                                                                                            Pageable pageable);
    //    Cường ( search đấu giá thành công )
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContainingAndIsWinner(Long bidderId,String productName,
                                                                          Boolean isWinner,
                                                                          Pageable pageable);
    //    Cường ( search đấu giá thất bại )
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContainingAndAuction_AuctionStatus_NameAndIsWinner (Long bidderId,
                                                                                                        String productName,
                                                                                                        String auctionStatusName,
                                                                                                        Boolean isWinner,
                                                                                                        Pageable pageable);
}
