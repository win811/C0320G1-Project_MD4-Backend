package md4.bid_project.services.impl;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.models.User;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
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
        if (user != null) {
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
        for (User testUser : users) {
            if (!user.getEmail().equals(userDto.getEmail().trim()) && testUser.getEmail().equals(userDto.getEmail().trim())) {
                messages.add("Email này đã được đăng kí. Vui lòng nhập lại email khác.");
                break;
            }
        }
        user.setEmail(userDto.getEmail().trim());
        if (!userDto.getPassword().equals("")) {
            if (!userDto.getNewPassword().equals("")) {
                if (BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(userDto.getNewPassword()));
                } else {
                    messages.add("Mật khẩu bạn nhập không đúng. Xin vui lòng nhập lại.");
                }
            } else {
                messages.add("Vui lòng nhập mật khẩu mới và xác nhận mật khẩu.");
            }
        }
        userDto.setBackendMessage(messages);
        if (userDto.getBackendMessage().size() == 0) {
            userRepository.save(user);
        }
    }

    //CREATE BY ANH DUC
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //CREATE BY ANH DUC
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //CREATE BY ANH DUC
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User save2(User user) {
        return userRepository.save(user);
    }

    //CREATE BY ANH DUC
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    //CREATE BY ANH DUC
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //CREATE BY ANH DUC
    @Override
    public Optional<User> findById2(Long id) {
        return userRepository.findById(id);
    }

    //CREATE BY ANH DUC
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    //CREATE BY ANH DUC
    @Override
    public void sendMail(String email, String title, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("Web Đấu Giá c03 <webdaugiac03@gmail.com>");
        msg.setTo(email);
        msg.setSubject(title);
        msg.setText(content);
        javaMailSender.send(msg);
    }

    //CREATE BY ANH DUC
    @Override
    public int getRandomIntegerWithinRange(int max, int min) {
        int spread = max - min;
        return new Random().nextInt(spread + 1) + min;
    }

    //CREATE BY ANH DUC
    @Override
    public boolean checkCode(String email, String code) {
        User user = userRepository.findByEmail(email);
        String codeSecurity = user.getPasswordResetCode().getCode();
        if (!codeSecurity.equals(code)) {
            return false;
        }
        return true;
    }

    //CREATE BY ANH DUC
    @Override
    public boolean checlExpiryDate(String email, Date timeNow) {
        java.sql.Timestamp sqlTS = new java.sql.Timestamp(timeNow.getTime());
        User user = userRepository.findByEmail(email);
        Date expiry = user.getPasswordResetCode().getExpiryDate();
        Long noTime = (sqlTS.getTime() - expiry.getTime()) / (60 * 1000);
        if (noTime > 15) {
            return false;
        }
        return true;
    }

    //CREATE BY ANH DUC
    @Override
    public String passwordEncryption(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean checkInfo(User user1, User user2) {
        if (user1.getPhoneNumber().equals(user2.getPhoneNumber()) && user1.getQuestion().equals(user2.getQuestion()) && user1.getAnswer().equals(user2.getAnswer())) {
            return true;
        }
        return false;
    }


}

