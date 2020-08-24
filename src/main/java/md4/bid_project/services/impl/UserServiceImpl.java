package md4.bid_project.services.impl;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;
    //Creator: Nguyễn Xuân Hùng
    @Override
    public UserUpdateDto findUserUpdateDtoByUserId(Long id) {
        UserUpdateDto userDto = new UserUpdateDto();
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            userDto.setFullName(user.getFullname());
            userDto.setEmail(user.getEmail());
            userDto.setGender(user.getGender());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setBirthday(user.getBirthday());
            userDto.setIdCard(user.getIdCard());
            userDto.setAddress(user.getAddress());
            return userDto;
        }
        return null;
    }
    //Creator: Nguyễn Xuân Hùng
    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    //Creator: Nguyễn Xuân Hùng
    @Override
    public void updateUser(UserUpdateDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);
        assert user != null;
        user.setFullname(userDto.getFullName().trim());
        user.setAddress(userDto.getAddress().trim());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setIdCard(userDto.getIdCard());
        user.setBirthday(userDto.getBirthday());
        List<User> users = userRepository.findAllByEmailContaining("");
        List<String> messages = new ArrayList<>();
        for(User testUser : users){
            if(!user.getEmail().equals(userDto.getEmail().trim())&&testUser.getEmail().equals(userDto.getEmail().trim())){
                messages.add("Email này đã được đăng kí. Vui lòng nhập lại email khác.");
                break;
            }
        }
        user.setEmail(userDto.getEmail().trim());
        if(!userDto.getPassword().equals("")){
            if(!userDto.getNewPassword().equals("")){
                if(BCrypt.checkpw(userDto.getPassword(),user.getPassword())){
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(userDto.getNewPassword()));
                }else {
                    messages.add("Mật khẩu bạn nhập không đúng. Xin vui lòng nhập lại.");
                }
            }else {
                messages.add("Vui lòng nhập mật khẩu mới và xác nhận mật khẩu.");
            }
        }
        userDto.setBackendMessage(messages);
        if(userDto.getBackendMessage().size()==0){
            userRepository.save(user);
        }
    }

    //B-Hoàng Long method
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    //B-Hoàng Long method
    @Override
    public Page<User> pageFindAllSearchFullName(Pageable pageable, String search) {
        return this.userRepository.findAllByAndIsLockedIsFalseAndFullnameContaining(pageable, search);
    }
    //B-Hoàng Long method
    @Override
    public User saveUser(User user) {
        this.userRepository.save(user);
        return user;
    }


}
