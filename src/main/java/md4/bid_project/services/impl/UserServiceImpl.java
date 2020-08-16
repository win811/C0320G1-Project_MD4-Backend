package md4.bid_project.services.Impl;

import md4.bid_project.dto.UserDto;
import md4.bid_project.models.Account;
import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.User;
import md4.bid_project.repositories.AccountRepository;
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
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDto findUserDtoByUserId(Long id) {
        UserDto userDto = new UserDto();
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            Account account = accountRepository.findAccountByUserId(user.getId());
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

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(null);
        Account account = accountRepository.findAccountByUserId(userDto.getId());
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
                if(BCrypt.checkpw(userDto.getPassword(),account.getPassword())){
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    account.setPassword(encoder.encode(userDto.getPassword()));
                }else {
                    messages.add("Mật khẩu bạn nhập không đúng. Xin vui lòng nhập lại.");
//                    userDto.setBackendMessage();
//                    return;
                }
            }else {
                messages.add("Vui lòng nhập mật khẩu mới và xác nhận mật khẩu.");
//                userDto.setBackendMessage();
//                return;
            }
        }
        userDto.setBackendMessage(messages);
        if(userDto.getBackendMessage().size()==0){
            userRepository.save(user);
        }
    }
}
