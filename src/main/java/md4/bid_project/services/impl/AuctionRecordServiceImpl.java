package md4.bid_project.services.impl;


import md4.bid_project.models.AuctionRecord;
import md4.bid_project.repositories.AuctionRecordRepository;
import md4.bid_project.services.AuctionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionRecordServiceImpl implements AuctionRecordService {

    @Autowired
    AuctionRecordRepository auctionRecordRepository;

    //    Creator : Cường
    @Override
    public Page<AuctionRecord> findByBidderIdAndProductNameAndRecordStatusName(Long bidderId, String productName, String recordStatusName, Pageable pageable) {

        switch (recordStatusName) {
            case "đang đấu giá":
                return auctionRecordRepository.findByBidder_IdAndAuction_Product_NameContainingAndAuction_AuctionStatus_Name
                        (bidderId, productName, recordStatusName, pageable);
            case "đấu giá thành công":
                return auctionRecordRepository.findByBidder_IdAndAuction_Product_NameContainingAndIsWinner
                        (bidderId, productName, Boolean.TRUE, pageable);
            case "đấu giá thất bại":
                String auctionStatusName = "đấu giá thành công";
                return auctionRecordRepository.findByBidder_IdAndAuction_Product_NameContainingAndAuction_AuctionStatus_NameAndIsWinner
                        (bidderId, productName, auctionStatusName, Boolean.FALSE, pageable);
            default:
                return auctionRecordRepository.findByBidder_IdAndAuction_Product_NameContaining
                        (bidderId, productName, pageable);
        }
    }

    @Override
    public List<AuctionRecord> getAllAuctionRecords() {
        return auctionRecordRepository.findAll();
    }

    @Override
    public AuctionRecord getAuctionRecordById(Long id) {
        return auctionRecordRepository.findById(id).orElse(null);
    }

    @Override
    public void createAuctionRecord(AuctionRecord auctionRecord) {
        auctionRecordRepository.save(auctionRecord);

    }

    @Override
    public void editAuctionRecord(AuctionRecord auctionRecord) {
        auctionRecordRepository.save(auctionRecord);
    }

    @Override
    public void deleteAuctionRecord(Long id) {
        auctionRecordRepository.deleteById(id);
    }
}
