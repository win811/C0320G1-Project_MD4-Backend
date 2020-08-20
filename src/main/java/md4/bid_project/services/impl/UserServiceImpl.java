package md4.bid_project.services.impl;

import md4.bid_project.models.User;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    //CREATE BY ANH DUC
    @Autowired
    private UserRepository userRepository;
    //CREATE BY ANH DUC
    @Autowired
    private JavaMailSender javaMailSender;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
        return password + "abcxyz";
    }

    @Override
    public Boolean checkInfo(User user1, User user2) {
        if (user1.getPhoneNumber().equals(user2.getPhoneNumber()) && user1.getQuestion().equals(user2.getQuestion()) && user1.getAnswer().equals(user2.getAnswer())) {
            return true;
        }
        return false;
    }


}

