package md4.bid_project.services;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserUpdateDto;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDto findUserUpdateDtoByUserId(Long id);
    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);
    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDto userDto);

    User findByEmail(String email);
}
