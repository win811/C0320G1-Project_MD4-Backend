package md4.bid_project.services.impl;

import md4.bid_project.models.Auction;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public List<Auction> getAllAuction() {
        return auctionRepository.findAll();
    }
}
