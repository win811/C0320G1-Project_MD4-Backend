package md4.bid_project.services.impl;
// creator: Hoai Ngan team C
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
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElse(null);
    }

    @Override
    public void createAuction(Auction auction) {
        auctionRepository.save(auction);
    }

    @Override
    public void editAuction(Auction auction) {
        auctionRepository.save(auction);
    }

    @Override
    public void deleteAuction(Long id) {
        auctionRepository.deleteById(id);
    }

    @Override
    public List<Auction> findAuctionsByStatusId(Long id) {
        return this.auctionRepository.findAllByAuctionStatus_Id(id);
    }

    @Override
    public List<Auction> findTopAuctions() {
        return this.auctionRepository.findTop5Auctions();
    }

    @Override
    public List<Auction> findAllAuctionByStatusAndCategoryName(Long id, String name) {
        return this.auctionRepository.findAuctionsByStatusIdAndCategoryName(id,name);
    }

    //BHung
    @Override
    public List<Auction> findAllAuctionByProductNameAndCategoryNameAndPriceRange(String productName, String categoryName, String price) {
        int index = price.indexOf("-");
        long from = Long.parseLong(price.substring(0,index));
        long end = Long.parseLong(price.substring(index+1,price.length()));
        return auctionRepository.findAllAuctionsByProductNameAndCategoryNameAndPriceRange(productName,categoryName,from,end);
    }

    @Override
    public List<Auction> findAllAuctionsByProductNameAndCategoryName(String productName, String categoryName) {
        return auctionRepository.findAllAuctionsByProductNameAndCategoryName(productName,categoryName);
    }

    @Override
    public List<Auction> findALlAuctionsByProductNameAndCategoryNameAndPriceMoreThan(String productName, String categoryName, String price) {
        long newPrice = Long.parseLong(price);
        return auctionRepository.findAllAuctionsByProductNameAndCategoryNameAndPriceMoreThan(productName,categoryName,newPrice);
    }
}
