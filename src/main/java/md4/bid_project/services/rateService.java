package md4.bid_project.services;



import md4.bid_project.models.Rate;

import java.util.List;

public interface rateService {
    List<Rate> findAll();
    Rate findById(Long id);
    void save(Rate rate);
    void removeById(Long id);
}
