package md4.bid_project.services.impl;

import md4.bid_project.models.Auction;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElse(null);
    }
}
