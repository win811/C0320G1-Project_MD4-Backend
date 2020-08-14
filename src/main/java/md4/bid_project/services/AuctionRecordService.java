package md4.bid_project.services;

import md4.bid_project.models.AuctionRecord;

import java.util.List;

public interface AuctionRecordService {

    List<AuctionRecord> getAllAuctionRecords();

    AuctionRecord getAuctionRecordById(Long id);

    void createAuctionRecord (AuctionRecord auctionRecord);

    void editAuctionRecord (AuctionRecord auctionRecord);

    void deleteAuctionRecord (Long id);

}
