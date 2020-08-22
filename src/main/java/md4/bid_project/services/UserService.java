package md4.bid_project.services;

import md4.bid_project.models.dto.UserUpdateDTO;
import md4.bid_project.models.User;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDTO findUserUpdateDtoByUserId(Long id);
    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);
    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDTO userDto);
    User findByEmail(String email);
}
