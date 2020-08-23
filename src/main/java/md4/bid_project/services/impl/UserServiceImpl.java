package md4.bid_project.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md4.bid_project.models.Rate;
import md4.bid_project.models.Role;
import md4.bid_project.models.dto.UserRegistrationDto;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public UserUpdateDto findUserUpdateDtoByUserId(Long id) {
        UserUpdateDto userDto = new UserUpdateDto();
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            userDto.setFullName(user.getFullName());
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
        user.setFullName(userDto.getFullName().trim());
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
//Creator: Trương Khánh Mậu
    @Override
    public void createUser(UserRegistrationDto userDto) {
        User user = new User();
        Role role = new Role();
        Rate rate=new Rate();
        rate.setId(5L);
        role.setId(1L);
        user.setRole(role);
        user.setStatus(true);
        user.setPoint(0L);
        user.setRate(rate);
        user.setIsLocked(false);
        user.setFullName(userDto.getFullName().trim());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress().trim());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setIdCard(userDto.getIdCard());
        user.setBirthday(userDto.getBirthday());
        user.setPassword(userDto.getPassword());
        user.setQuestion(userDto.getQuestion());
        user.setAnswer(userDto.getAnswer());
        userRepository.save(user);

    }

    @Override
    public Optional<User> checkUniqueEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> checkUniquePhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }


    @Override
    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
        return null;
    }

}
