package md4.bid_project.services;

import md4.bid_project.models.FavoriteProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteProductService {

    // Create: Toàn
    Page<FavoriteProduct> findByUserID(Long userId, Pageable pageable);

    // Create: Toàn
    FavoriteProduct createFavoriteProduct(FavoriteProduct favoriteProduct);

    // Create: Toàn
    void deleteFavoriteProduct(Long favoriteProductId);
}
