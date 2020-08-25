package md4.bid_project.services;

import md4.bid_project.models.User;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserRegistrationDto;
import md4.bid_project.models.dto.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface UserService {

    //Bach
    User getUserById(Long id);

    //Creator: Nguyễn Xuân Hùng
    UserUpdateDTO findUserUpdateDtoByUserId(Long id);

    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);

    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDTO userDto);

    //Creator: Quốc Tùng
    Page<UserListDTO> findAll(int page);
    //Page<UserListDTO> searchFiveFields(Long id, String fullname, String email, String address, String rateName, int page);

    Page<UserListDTO> findCustomerByCriteria(Specification<User> spec, int page);

    Specification<User> getFilter(String id, String fullname, String email, String address, String rateName);

    User findByEmail(String email);

    //Creator: Trương Khánh Mậu
    void createUser(UserRegistrationDto userDto);

    //Creator: Trương Khánh Mậu
    User checkUniqueEmail(String email);

    //Create: Trương Khánh Mạu
    Optional<User> checkUniquePhone(String phoneNumber);

    //B-Hoàng Long method
    User findById(Long id);
    //B-Hoàng Long method
    Page<User> pageFindAllSearchFullName(Pageable pageable, String search);
    //B-Hoàng Long method
    User saveUser(User user);
}
