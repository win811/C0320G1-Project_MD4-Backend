package md4.bid_project.services.impl;

import md4.bid_project.models.*;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.AuctionService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    ProductRepository productRepository;

    //    Cường
    @Override
    public Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable) {
        return productRepository.findByOwner_IdAndNameContainingAndApprovementStatus_NameContaining(ownerId,productName,approvementStatusName,pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findProductByOwnerId(Long ownerId) {
        return null;
    }
    //Thành
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
    //Thành
    @Override
    public void save(Product product) {
        User user  = new User();
        user.setId(1L);
        ApprovementStatus status = new ApprovementStatus();
        status.setId(1L);
        product.setApprovementStatus(status);
        product.setStatus(true);
        product.setOwner(user);
        productRepository.save(product);
        // add auction
        Auction auction = new Auction();
        auction.setProduct(product);
        AuctionStatus auctionStatus = new AuctionStatus();
        auctionStatus.setId(1L);
        auction.setAuctionStatus(auctionStatus);
        auctionRepository.save(auction);
    }
    //Thành
    @Override
    public Optional<Product> findProductById(Long productId) {
        return productRepository.findAllById(productId);
    }
}
