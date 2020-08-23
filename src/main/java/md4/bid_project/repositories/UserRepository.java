package md4.bid_project.repositories;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


//Tùng

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findAllByEmailContaining(String email);

//    @Query(value = "select * from bid_project.user_list_dto where id = %?1%  and full_name like  %?2% " +
//            "and email like  %?3% and address like  %?4% and rate like  %?5%",
//            nativeQuery = true)
//    List<UserListDTO> queryByFiveFields(String id, String fullname, String email, String address, String rate);

    //Page<User> findAllByIdAndFullnameContainingAndEmailContainingAndAddressContainingAndRate_NameContaining(Long id, String fullname, String email, String address, String rateName, Pageable pageable);

    //List<UserListDTO> findAllByIdContainingOrFullnameContainingOrEmailContainingOrAddressContainingOrRateContaining(String id, String fullname, String email, String address, String rate);
}
