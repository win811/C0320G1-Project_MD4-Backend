package md4.bid_project.repositories;

import md4.bid_project.models.AuctionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuctionRecordRepository extends JpaRepository<AuctionRecord, Long> {

    //    Creator : Cường (search tất cả)
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContaining(Long bidderId,String productName, Pageable pageable);
    //    Creator : Cường (search đang đấu giá)
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContainingAndAuction_AuctionStatus_Name(Long bidderId, String productName,
                                                                                            String auctionStatusName,
                                                                                            Pageable pageable);
    //    Creator : Cường ( search đấu giá thành công )
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContainingAndIsWinner(Long bidderId,String productName,
                                                                          Boolean isWinner,
                                                                          Pageable pageable);
    //    Creator : Cường ( search đấu giá thất bại )
    Page<AuctionRecord> findByBidder_IdAndAuction_Product_NameContainingAndAuction_AuctionStatus_NameAndIsWinner (Long bidderId,
                                                                                                        String productName,
                                                                                                        String auctionStatusName,
                                                                                                        Boolean isWinner,
                                                                                                        Pageable pageable);
}
