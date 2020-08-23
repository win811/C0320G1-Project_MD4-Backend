package md4.bid_project.services;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserRegistrationDto;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDto findUserUpdateDtoByUserId(Long id);
    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);
    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDto userDto);

    //Creator: Trương Khánh Mậu
    void createUser(UserRegistrationDto userDto);

    //Creator: Trương Khánh Mậu
    Optional<User> checkUniqueEmail(String email);

    //Create: Trương Khánh Mạu
    Optional<User> checkUniquePhone(String phoneNumber);
}
