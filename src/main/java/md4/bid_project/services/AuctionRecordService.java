package md4.bid_project.services;
// creator: Hoai Ngan team C
import md4.bid_project.models.Auction;
import md4.bid_project.models.AuctionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionRecordService {

    //    Cường
    Page<AuctionRecord> findByBidderIdAndProductNameAndRecordStatusName(Long bidderId,String productName,
                                                                        String recordStatusName,Pageable pageable);

    List<AuctionRecord> getAllAuctionRecords();

    AuctionRecord getAuctionRecordById(Long id);

    void createAuctionRecord (AuctionRecord auctionRecord);

    void editAuctionRecord (AuctionRecord auctionRecord);

    void deleteAuctionRecord (Long id);

    List<AuctionRecord> getTopAuctionRecords(Long auctionId);

    AuctionRecord getRecordHavingBestPrice(Long auctionId);

    AuctionRecord findByAuctionIdAndBidderId(Long auctionId, Long bidderId);

}
