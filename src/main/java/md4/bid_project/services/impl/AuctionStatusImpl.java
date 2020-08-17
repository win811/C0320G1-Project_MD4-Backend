package md4.bid_project.services.impl;

import md4.bid_project.models.AuctionStatus;
import md4.bid_project.repositories.AuctionStatusRepository;
import md4.bid_project.services.AuctionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionStatusImpl implements AuctionStatusService {
    @Autowired
    AuctionStatusRepository auctionStatusRepository;

    @Override
    public List<AuctionStatus> getAllAuctionStatus() {
        return auctionStatusRepository.findAll();
    }
}
