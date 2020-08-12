package md4.bid_project.services.Impl;

import md4.bid_project.models.Rate;
import md4.bid_project.repositories.rateRepository;
import md4.bid_project.services.ratesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ratesServiceImpl implements ratesService {

    @Autowired
    private rateRepository rateRepository;


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
