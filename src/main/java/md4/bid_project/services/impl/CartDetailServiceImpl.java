package md4.bid_project.services.impl;

import md4.bid_project.models.CartDetail;
import md4.bid_project.repositories.CartDetailRepository;
import md4.bid_project.services.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    CartDetailRepository cartDetailRepository;
    @Override
    public List<CartDetail> findCartDetailByCartId(Long id) {
        return cartDetailRepository.findAllByCart_Id(id);
    }
}
