package md4.bid_project.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import md4.bid_project.models.dto.UserUpdateDTO;
import md4.bid_project.models.User;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class UserServiceImpl implements UserService {
    Long id;
    private String username;

    private String email;

    @JsonIgnore
    private String password;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;
    //Creator: Nguyễn Xuân Hùng
    @Override
    public UserUpdateDTO findUserUpdateDtoByUserId(Long id) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            userUpdateDTO.setFullName(user.getFullname());
            userUpdateDTO.setEmail(user.getEmail());
            userUpdateDTO.setGender(user.getGender());
            userUpdateDTO.setPhoneNumber(user.getPhoneNumber());
            userUpdateDTO.setBirthday(user.getBirthday());
            userUpdateDTO.setIdCard(user.getIdCard());
            userUpdateDTO.setAddress(user.getAddress());
            return userUpdateDTO;
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
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userUpdateDTO.getId()).orElse(null);
        assert user != null;
        user.setFullname(userUpdateDTO.getFullName().trim());
        user.setAddress(userUpdateDTO.getAddress().trim());
        user.setGender(userUpdateDTO.getGender());
        user.setIdCard(userUpdateDTO.getIdCard());
        user.setBirthday(userUpdateDTO.getBirthday());
        List<User> users = userRepository.findAllByIsLockedIsFalse();
        List<String> messages = new ArrayList<>();
        for(User testUser : users){
            if(!user.getEmail().equals(userUpdateDTO.getEmail().trim())&&testUser.getEmail().equals(userUpdateDTO.getEmail().trim())){
                messages.add("Email này đã được đăng kí. Vui lòng nhập lại email khác.");
                break;
            }
        }
        user.setEmail(userUpdateDTO.getEmail().trim());
        if(!userUpdateDTO.getPassword().equals("")){
            if(!userUpdateDTO.getNewPassword().equals("")){
                if(BCrypt.checkpw(userUpdateDTO.getPassword(),user.getPassword())){
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(userUpdateDTO.getNewPassword()));
                }else {
                    messages.add("Mật khẩu hiện tại không đúng. Xin vui lòng nhập lại.");
                }
            }else {
                messages.add("Vui lòng nhập mật khẩu hiện tại đi kèm với mật khẩu mới và xác nhận mật khẩu.");
            }
        } else if(!userUpdateDTO.getNewPassword().equals("")){
            messages.add("Vui lòng nhập mật khẩu hiện tại khi đổi mật khẩu.");
        }

        for(User testUser : users){
            if(!user.getPhoneNumber().equals(userUpdateDTO.getPhoneNumber())&&testUser.getPhoneNumber().equals(userUpdateDTO.getPhoneNumber())){
                messages.add("Số điện thoại này đã được đăng kí. Vui lòng nhập lại số điện thoại khác.");
                break;
            }
        }
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        userUpdateDTO.setBackendMessage(messages);
        if(userUpdateDTO.getBackendMessage().size()==0){
            userRepository.save(user);
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
