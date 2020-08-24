package md4.bid_project.services;


import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import md4.bid_project.models.dto.UserRegistrationDto;

import javax.swing.text.html.Option;
import java.util.Optional;

import java.util.List;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDto findUserUpdateDtoByUserId(Long id);

    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);

    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDto userDto);

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
}
