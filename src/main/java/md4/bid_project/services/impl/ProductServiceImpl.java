package md4.bid_project.services.impl;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.models.dto.ProductSearchField;
import md4.bid_project.repositories.ProductRepository;
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


@Service
public class ProductServiceImpl implements ProductService {

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
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        product.setStatus(true);
        productRepository.save(product);
    }

    //Thành Long

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
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

    @Override
    public Page<Product> findCustomerByCriteria(Specification<Product> spec, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public Specification<Product> getFilter(ProductSearchField search) {
        List<ProductSpecification> specs = new ArrayList<>();
        Specification<Product> spec;
        // search theo
        // product name
        if(search.getName() != null && !"".equals(search.getName())) {
            specs.add(new ProductSpecification(new SearchCriteria("name", "like", search.getName())));
        }
        //category
        if(search.getCategory() != null && !"".equals(search.getCategory()) ) {
            specs.add(new ProductSpecification(new SearchCriteria("name", "category-join", search.getCategory())));
        }

        // auction status
        if(search.getStatus() != null && !"".equals(search.getStatus())) {
            specs.add(new ProductSpecification(new SearchCriteria("name", "auction-join", search.getStatus())));
        }

        // price between
        if(search.getMinPrice() != null && search.getMaxPrice() != null) {
            // trường hợp lớn hơn > x.xxx.xxx vnd
            if(search.getMaxPrice().equals("max")) {
                specs.add(new ProductSpecification(new SearchCriteria("initialPrice", "gt", search.getMinPrice())));
            } else {
                specs.add(new ProductSpecification(new SearchCriteria("initialPrice", "between", search.getMinPrice(), search.getMaxPrice())));
            }
        }
        // owner
        if(search.getOwner() != null && !"".equals(search.getOwner())) {
            specs.add(new ProductSpecification(new SearchCriteria("fullname", "user-join", search.getOwner())));
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
