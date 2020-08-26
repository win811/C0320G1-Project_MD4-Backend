package md4.bid_project.services.impl;

import md4.bid_project.models.*;
import md4.bid_project.models.dto.ProductListDTO;
import md4.bid_project.repositories.AuctionRepository;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductService;
import md4.bid_project.services.searchProduct.ProductSpecification;
import md4.bid_project.services.searchProduct.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    ProductRepository productRepository;

    // Creator : Cường
    @Override
    public Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName,
            String approvementStatusName, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC,"registerDate","id");
        Pageable pageable = PageRequest.of(page,4,sort);
        return productRepository.findByOwner_IdAndNameContainingAndApprovementStatus_NameContaining(ownerId,
                productName, approvementStatusName, pageable);
    }

    // Creator : Cường

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Creator : Cường
    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
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
        ApprovementStatus status = new ApprovementStatus();
        status.setId(1L);
        product.setApprovementStatus(status);
        product.setStatus(true);
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
        product.setStatus(false);
        productRepository.save(product);
    }

    //Thành Long
    private ProductListDTO transferToDTO(Product temp) {
        ProductListDTO product = new ProductListDTO();
        product.setId(temp.getId());
        product.setName(temp.getName());
        product.setCategory(temp.getCategory().getName());
        product.setInitialPrice(temp.getInitialPrice());
        product.setIncreaseAmount(temp.getIncreaseAmount());
        product.setApprovementStatus(temp.getApprovementStatus().getName());
        product.setAuctionStatus(temp.getAuction().getAuctionStatus().getName());
        product.setOwner(temp.getOwner().getFullName());
        product.setRegisterDate(temp.getRegisterDate());
        product.setStartDate(temp.getStartDate());
        product.setEndDate(temp.getEndDate());
        product.setProductImages(temp.getProductImages());
        product.setBanned(temp.getBanned());
        product.setDescription(temp.getDescription());
        return product;
    }
    private Page<ProductListDTO> transferToNewPage(Page<Product> products) {
        Product temp;
        List<ProductListDTO> productDTO = new ArrayList<>();
        Iterator iterator = products.iterator();
        while (iterator.hasNext()) {
            temp = (Product)iterator.next();
            productDTO.add(transferToDTO(temp));
        }
        Page<ProductListDTO> _product = new PageImpl<>(productDTO, products.getPageable(), products.getTotalElements());
        return _product;
    }

    @Override
    public Page<ProductListDTO> findAllProduct(int page) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<Product> products = productRepository.findAllByStatusIsTrue(pageable);
        return transferToNewPage(products);
    }

    @Override
    public ProductListDTO checkProduct(Long id) {
        Product product = productRepository.getOne(id);
        return transferToDTO(product);
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

    // Created by: Toàn
    @Override
    public Page<Product> findApprovedProductsByUserId(Long userId, Pageable pageable) {
        return productRepository.findByOwner_IdAndApprovementStatus_Id(userId, APPROVEMENT_STATUS_SUCCESS, pageable);
    }

    // Created by: Toàn
    @Override
    public Page<Product> findWaitingProductsByUserId(Long userId, Pageable pageable) {
        return productRepository.findByOwner_IdAndApprovementStatus_Id(userId, APPROVEMENT_STATUS_WAITING, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //Thành Long
    @Override
    public Page<ProductListDTO> findCustomerByCriteria(Specification<Product> spec, int page) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<Product> products = productRepository.findAll(spec, pageable);
        return transferToNewPage(products);
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
