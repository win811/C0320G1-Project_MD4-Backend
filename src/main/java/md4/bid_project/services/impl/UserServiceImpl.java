package md4.bid_project.services.impl;

import md4.bid_project.models.Rate;
import md4.bid_project.models.Role;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserRegistrationDto;
import md4.bid_project.models.dto.UserUpdateDTO;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import md4.bid_project.services.search.SearchCriteria;
import md4.bid_project.services.search.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;

    //Creator: Nguyễn Xuân Hùng
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserUpdateDTO findUserUpdateDtoByUserId(Long id) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
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

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public void updateUser(UserUpdateDTO userDto) {

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
        return null;
    }


    //Creator: Trương Khánh Mậu
    @Override
    public void createUser(UserRegistrationDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        Role role = new Role();
        Rate rate = new Rate();
        rate.setId(4L);
        role.setId(1L);
        user.setRole(role);
        user.setStatus(true);
        user.setPoint(0L);
        user.setRate(rate);
        user.setIsLocked(false);
        user.setFullname(userDto.getFullName().trim());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress().trim());
        user.setGender(userDto.getGender());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setIdCard(userDto.getIdCard());
        user.setBirthday(userDto.getBirthday());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setQuestion(userDto.getQuestion());
        user.setAnswer(userDto.getAnswer());
        userRepository.save(user);


    }

    @Override
    public User checkUniqueEmail(String email) {
        return userRepository.findByEmail(email);
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
    public User findByPhoneNumberByDuc(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User checkUniquePhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }


    // Creator: Tùng
    @Override
    public Page<UserListDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id"));
        Page<User> users = userRepository.findAll(pageable);
        return transferToDTO(users);
    }


    @Override
    public Page<UserListDTO> findCustomerByCriteria(Specification<User> specs, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id"));
        Page<User> users = userRepository.findAll(specs, pageable);
        return transferToDTO(users);
    }

    @Override
    public Specification<User> getFilter(String id, String fullname, String email, String address, String
            rateName) {
        List<UserSpecification> specs = new ArrayList<>();
        Specification<User> spec;
        // search theo
        // product fullname
        if (fullname != null && !"undefined".equals(fullname) && !"".equals(fullname)) {
            specs.add(new UserSpecification(new SearchCriteria("fullname", "like", fullname)));
        }
        if (email != null && !"undefined".equals(email) && !"".equals(email)) {
            specs.add(new UserSpecification(new SearchCriteria("email", "like", email)));
        }
        if (address != null && !"undefined".equals(address) && !"".equals(address)) {
            specs.add(new UserSpecification(new SearchCriteria("address", "like", address)));
        }
        if (rateName != null && !"undefined".equals(rateName) && !"".equals(rateName)) {
            specs.add(new UserSpecification(new SearchCriteria("name", "rate-join", rateName)));
        }
        if (id != null && !"undefined".equals(id) && !"".equals(id)) {
            specs.add(new UserSpecification(new SearchCriteria("id", "equal", id)));
        }
        if (specs.size() != 0) {
            spec = Specification.where(specs.get(0));
            for (int i = 1; i < specs.size(); i++) {
                assert spec != null;
                spec = spec.and(specs.get(i));
            }
            return spec;
        }
        return null;
    }

    private Page<UserListDTO> transferToDTO(Page<User> users) {
        Iterator iterator = users.iterator();
        List<UserListDTO> userListDTO = new ArrayList<UserListDTO>();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            UserListDTO userListDTO1 = new UserListDTO();
            userListDTO1.setId(user.getId());
            userListDTO1.setFullname(user.getFullname());
            userListDTO1.setEmail(user.getEmail());
            userListDTO1.setAddress(user.getAddress());
            userListDTO1.setPhoneNumber(user.getPhoneNumber());
            userListDTO1.setLastLogin(user.getLastLogin());
            userListDTO1.setPoint(user.getPoint());
            userListDTO1.setRate(user.getRate().getName());
            userListDTO.add(userListDTO1);
        }
        return new PageImpl<UserListDTO>(userListDTO, users.getPageable(), users.getTotalElements());
    }

    //B-Hoàng Long method
//            @Override
//            public User findById (Long id){
//                return userRepository.findById(id).orElse(null);
//            }
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

