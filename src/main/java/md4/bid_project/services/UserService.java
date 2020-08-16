package md4.bid_project.services;

import md4.bid_project.dto.UserDto;
import md4.bid_project.models.User;

public interface UserService {
    UserDto findUserDtoByUserId(Long id);
    User findUserById(Long id);
    void updateUser(UserDto userDto);
}
