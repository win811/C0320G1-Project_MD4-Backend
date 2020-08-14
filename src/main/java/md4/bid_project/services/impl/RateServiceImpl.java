package md4.bid_project.services.impl;

import md4.bid_project.models.Rate;
import md4.bid_project.repositories.RateRepository;
import md4.bid_project.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public Rate findById(Long id) {
        return rateRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Rate rate) {
        rateRepository.save(rate);
    }

    @Override
    public void removeById(Long id) {
        rateRepository.deleteById(id);
    }
}
