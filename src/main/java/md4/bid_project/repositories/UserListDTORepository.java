package md4.bid_project.repositories;

import md4.bid_project.models.dto.UserListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserListDTORepository extends JpaRepository<UserListDTO, Long> {


    //Creater: Lâm Quốc Tùng
    @Query(value = "select * from bid_project.user_list_dto where id like %?1%  and full_name like  %?2% " +
            "and email like  %?3% and address like  %?4% and rate like  %?5%",
            nativeQuery = true)
    List<UserListDTO> queryByFiveFields(String id, String fullname, String email, String address, String rate);

    List<UserListDTO> findAllByIdContainingOrFullnameContainingOrEmailContainingOrAddressContainingOrRateContaining(String id, String fullname, String email, String address, String rate);

}