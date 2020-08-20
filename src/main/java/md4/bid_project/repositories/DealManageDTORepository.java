package md4.bid_project.repositories;

import md4.bid_project.models.dto.DealManageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//all created by Thao
@Repository
public interface DealManageDTORepository extends JpaRepository<DealManageDTO, Long> {

    //search by all 5 fields
    @Query(value = "select * from bid_project.deal_manage_dto_VIEW where name_buyer like %?1%  and name_seller like %?2% " +
            "and name_product like %?3% and total_payment >= ?4 and status_of_deal like %?5%",
            nativeQuery = true)
    List<DealManageDTO> queryByFiveFields(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status, Pageable pageable);

    @Query(value = "select count(*) from bid_project.deal_manage_dto_VIEW where name_buyer like %?1%  and name_seller like %?2% " +
            "and name_product like %?3% and total_payment >= ?4 and status_of_deal like %?5%",
            nativeQuery = true)
    int countSearchResult(String nameBuyer, String nameSeller, String nameProduct, Double totalPayment, String status);

}
