package md4.bid_project.services.impl;


import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserDTO;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setBirthday(userDTO.getBirthday());
        user.setIdCard(userDTO.getIdCard());
        user.setGender(userDTO.getGender());
        user.setRate(userDTO.getRate());
        user.setPoint(userDTO.getPoint());
        user.setLastLogin(userDTO.getLastLogin());
        user.setStatus(userDTO.getStatus());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setQuestion(userDTO.getQuestion());
        user.setAnswer(userDTO.getAnswer());
        user.setReasonBan(userDTO.getReasonBan());
        user.setIsLocked(userDTO.getIsLocked());
        userRepository.save(user);
    }
}
