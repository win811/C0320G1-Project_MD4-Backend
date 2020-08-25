package md4.bid_project.services.search;

import md4.bid_project.models.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private final SearchCriteria criteria;

    public ProductSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(criteria.getOperation().equalsIgnoreCase("like")) {
            // value like %chuỗi_tìm_kiếm%
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValues().get(0) + "%");
        } else if(criteria.getOperation().equalsIgnoreCase("between")) {
            // value between moneyFrom and moneyTo
            Double moneyFrom =  Double.valueOf(criteria.getValues().get(0));
            Double moneyTo=  Double.valueOf(criteria.getValues().get(1));
            return criteriaBuilder.between(root.get(criteria.getKey()), moneyFrom, moneyTo);
        } else if(criteria.getOperation().equalsIgnoreCase("gt")) {
            // value >= money
            Double money =  Double.valueOf(criteria.getValues().get(0));
            return criteriaBuilder.gt(root.get(criteria.getKey()), money);
        } else if(criteria.getOperation().equalsIgnoreCase("auction-join")) {
            // Tìm kiếm theo trạng thái auction
            Join<Product, Auction> auctionJoin = root.join("auction"); // inner join.
            Join<Auction, AuctionStatus> statusJoin = auctionJoin.join("auctionStatus");
            return criteriaBuilder.equal(statusJoin.get(criteria.getKey()), criteria.getValues().get(0));
        } else if(criteria.getOperation().equalsIgnoreCase("category-join")) {
            // tìm kiếm theo loại
            Join<Product, Category> categoryJoin = root.join("category"); // inner join.
            return criteriaBuilder.equal(categoryJoin.get(criteria.getKey()), criteria.getValues().get(0));
        }else if(criteria.getOperation().equalsIgnoreCase("user-join")) {
                // tìm kiếm owner
            Join<Product, User> userJoin = root.join("owner"); // inner join.
            return criteriaBuilder.equal(userJoin.get(criteria.getKey()), criteria.getValues().get(0));
        } else {
            return null;
        }
    }
}
