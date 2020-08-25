package md4.bid_project.services.search;

import md4.bid_project.models.Rate;
import md4.bid_project.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.script.ScriptEngine;

public class UserSpecification implements Specification<User> {

    private final SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase("like")) {
            // value like %chuỗi_tìm_kiếm%
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValues().get(0) + "%");
        } else if (criteria.getOperation().equalsIgnoreCase("equal")) {
            Long _id = Long.valueOf(criteria.getValues().get(0));
            return criteriaBuilder.equal(root.get(criteria.getKey()), _id);
        } else if(criteria.getOperation().equalsIgnoreCase("rate-join")) {
            // tìm kiếm owner
            Join<User, Rate> userJoin = root.join("rate"); // inner join.
            return criteriaBuilder.like(userJoin.get(criteria.getKey()), "%" + criteria.getValues().get(0) + "%");
        } else {
            return null;
        }
    }
}
