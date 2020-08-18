package md4.bid_project.services;

import md4.bid_project.models.Auction;

import java.util.List;

public interface AuctionService {

    List<Auction> getAllAuctions();

    Auction getAuctionById(Long id);

    void createAuction (Auction auctionRecord);

    void editAuction (Auction auctionRecord);

    void deleteAuction (Long id);

    //Bach
    Auction getAutionByProductId(Long productId);
}
