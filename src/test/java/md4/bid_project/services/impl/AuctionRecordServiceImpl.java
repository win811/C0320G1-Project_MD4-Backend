package md4.bid_project.services.impl;


import md4.bid_project.models.AuctionRecord;
import md4.bid_project.repositories.AuctionRecordRepository;
import md4.bid_project.services.AuctionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionRecordServiceImpl implements AuctionRecordService {

    @Autowired
    AuctionRecordRepository auctionRecordRepository;


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
