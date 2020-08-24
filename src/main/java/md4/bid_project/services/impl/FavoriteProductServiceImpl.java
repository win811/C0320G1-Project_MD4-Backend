package md4.bid_project.services.impl;

import md4.bid_project.models.FavoriteProduct;
import md4.bid_project.repositories.FavoriteProductRepository;
import md4.bid_project.services.FavoriteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavoriteProductServiceImpl implements FavoriteProductService {

    @Autowired
    private FavoriteProductRepository favoriteProductRepository;

    // Create: Toàn
    @Override
    public Page<FavoriteProduct> findByUserID(Long userId, Pageable pageable) {
        return favoriteProductRepository.findByUserIdAndStatusIsTrue(userId, pageable);
    }

    // Create: Toàn
    @Override
    public FavoriteProduct createFavoriteProduct(FavoriteProduct favoriteProduct) {
        return favoriteProductRepository.save(favoriteProduct);
    }

    // Create: Toàn
    @Override
    public void deleteFavoriteProduct(Long favoriteProductId) {
        favoriteProductRepository.deleteById(favoriteProductId);
    }
}
