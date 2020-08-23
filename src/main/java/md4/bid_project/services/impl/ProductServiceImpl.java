package md4.bid_project.services.impl;

import md4.bid_project.models.*;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.AuctionService;
import md4.bid_project.services.ProductService;
import md4.bid_project.services.searchProduct.ProductSpecification;
import md4.bid_project.services.searchProduct.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    ProductRepository productRepository;

    //    Creator : Cường
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

    @Override
    public void deleteProduct(Product product) {
        product.setStatus(true);
        productRepository.save(product);
    }

    //Thành Long
    @Override
    public Page<Product> findAllProduct(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return productRepository.findAllByStatusIsFalse(pageable);
    }

    //Thành Long
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    //Thành Long
    @Override
    public void approvementProduct(Product product, ApprovementStatus approve) {
        product.setApprovementStatus(approve);
        productRepository.save(product);
    }

    //Thành Long
    @Override
    public void unApprovementProduct(Product product, ApprovementStatus approve) {
        product.setApprovementStatus(approve);
        productRepository.save(product);
    }

    //Thành Long
    @Override
    public Page<Product> findCustomerByCriteria(Specification<Product> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return productRepository.findAll(spec, pageable);
    }

    //Thành Long
    @Override
    public Specification<Product> getFilter(String name, String category, String minPrice, String maxPrice, String owner, String status) {
        List<ProductSpecification> specs = new ArrayList<>();
        Specification<Product> spec;
        // search theo
        // product name
        if(!"".equals(name) && !"undefined".equals(name)) {
            specs.add(new ProductSpecification(new SearchCriteria("name", "like", name)));
        }
        //category
        if(!"".equals(category) && !"undefined".equals(category) ) {
            specs.add(new ProductSpecification(new SearchCriteria("name", "category-join", category)));
        }

        // auction status
        if(!"".equals(status) && !"undefined".equals(status)) {
            specs.add(new ProductSpecification(new SearchCriteria("name", "auction-join", status)));
        }

        // price between
        if(!"undefined".equals(minPrice) && !"undefined".equals(maxPrice) && !"".equals(minPrice) && !"".equals(maxPrice)) {
            // trường hợp lớn hơn > x.xxx.xxx vnd
            if(maxPrice.equals("max")) {
                specs.add(new ProductSpecification(new SearchCriteria("initialPrice", "gt", minPrice)));
            } else {
                specs.add(new ProductSpecification(new SearchCriteria("initialPrice", "between", minPrice, maxPrice)));
            }
        }
        // owner
        if(!"".equals(owner) && !"undefined".equals(owner)) {
            specs.add(new ProductSpecification(new SearchCriteria("fullname", "user-join", owner)));
        }
        if (specs.size() != 0) {
            spec = Specification.where(specs.get(0));
            for (int i = 1; i < specs.size(); i++) {
                assert spec != null;
                spec = spec.and(specs.get(i));
            }
            return spec;
        }
        return null;
    }

}
