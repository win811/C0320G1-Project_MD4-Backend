package md4.bid_project.services.Impl;

import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionRepository auctionRepository;
}
