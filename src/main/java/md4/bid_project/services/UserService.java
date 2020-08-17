package md4.bid_project.services;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDto findUserUpdateDtoByUserId(Long id);
    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);
    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDto userDto);

    //B-Hoàng Long method
    Page<User> getAllUserNotLock(String fullName, Pageable pageable);

    //B-Hoàng Long method
    void addUser(User user);
}
