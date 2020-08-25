package md4.bid_project.services;
// creator: Hoai Ngan team C
import md4.bid_project.models.Auction;

import java.util.List;

public interface AuctionService {

    List<Auction> getAllAuctions();

    Auction getAuctionById(Long id);

    void createAuction (Auction auctionRecord);

    void editAuction (Auction auctionRecord);

    void deleteAuction (Long id);

    //Creator:BHùng
    List<Auction> findAuctionsByStatusId(Long id);

    //Creator:BHùng
    List<Auction> findTopAuctions();

    //Creator:BHùng
    List<Auction> findAllAuctionByStatusAndCategoryName(Long id,String name);

    //Creator:BHùng
    List<Auction> findAllAuctionByProductNameAndCategoryNameAndPriceRange(String productName,String categoryName,String price);

    //Creator:BHùng
    List<Auction> findAllAuctionsByProductNameAndCategoryName(String productName, String categoryName);

    //Creator: BHung
    List<Auction> findALlAuctionsByProductNameAndCategoryNameAndPriceMoreThan(String productName,String categoryName,String price);

    //Bach
    Auction getAutionByProductId(Long productId);
}
