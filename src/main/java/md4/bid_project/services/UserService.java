package md4.bid_project.services;


import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;

import java.util.List;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDto findUserUpdateDtoByUserId(Long id);

    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);

    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDto userDto);

    //Creator: Quốc Tùng
    List<UserListDTO> findAll();
}