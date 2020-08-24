package md4.bid_project.services;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {
    //Creator: Nguyễn Xuân Hùng
    UserUpdateDto findUserUpdateDtoByUserId(Long id);
    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);
    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDto userDto);

    //B-Hoàng Long method
    User findById(Long id);
    //B-Hoàng Long method
    Page<User> pageFindAllSearchFullName(Pageable pageable, String search);
    //B-Hoàng Long method
    User saveUser(User user);
}
