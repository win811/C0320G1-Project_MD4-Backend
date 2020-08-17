package md4.bid_project.repositories;

import md4.bid_project.models.CartDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {

    //created by Thao
    List<CartDetail> findAllByIsDeleteIsFalse(Pageable pageable);

    //created by Thao
    List<CartDetail> findAllByIsDeleteIsFalse();

    //created by Thao
    List<CartDetail> findAllByAuction_Product_OwnerContainingOrCart_User_FullnameContainingOrAuction_Product_NameContainingOrStatusContaining(String nameBuyer, String nameSeller, String nameProduct, String status);

////    public Page<AuctionRecord> findByBidderIdAndProductNameAndRecordStatusName
//    @Query(value = "select * from customer join province on province.id = customer.province_id where province.name like %?1% and (customer.first_name like  %?2% or customer.last_name like  %?3%)",nativeQuery = true)
//    Page<Customer> findAllByProvince_NameContainingAndFirstNameContainingOrLastNameContaining (String provinceName,String firstName,String lastName,Pageable pageable);
}
