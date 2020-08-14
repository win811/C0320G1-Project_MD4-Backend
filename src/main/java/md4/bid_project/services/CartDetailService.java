package md4.bid_project.services;

import md4.bid_project.models.CartDetail;

import java.util.List;

public interface CartDetailService {

    public List<CartDetail> findAll();

    public CartDetail findById(Long id);

}
