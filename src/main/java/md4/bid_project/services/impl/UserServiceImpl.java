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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    //Creator: Nguyễn Xuân Hùng
    @Override
    public UserUpdateDTO findUserUpdateDtoByUserId(Long id) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userUpdateDTO.setFullName(user.getFullName());
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
        user.setFullName(userUpdateDTO.getFullName().trim());
        user.setAddress(userUpdateDTO.getAddress().trim());
        user.setGender(userUpdateDTO.getGender());
        user.setIdCard(userUpdateDTO.getIdCard());
        user.setBirthday(userUpdateDTO.getBirthday());
        List<User> users = userRepository.findAllByIsLockedIsFalse();
        List<String> messages = new ArrayList<>();
        for (User testUser : users) {
            if (!user.getEmail().equals(userUpdateDTO.getEmail().trim()) && testUser.getEmail().equals(userUpdateDTO.getEmail().trim())) {
                messages.add("Email này đã được đăng kí. Vui lòng nhập lại email khác.");
                break;
            }
        }
        user.setEmail(userUpdateDTO.getEmail().trim());
        if (!userUpdateDTO.getPassword().equals("")) {
            if (!userUpdateDTO.getNewPassword().equals("")) {
                if (BCrypt.checkpw(userUpdateDTO.getPassword(), user.getPassword())) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(userUpdateDTO.getNewPassword()));
                } else {
                    messages.add("Mật khẩu hiện tại không đúng. Xin vui lòng nhập lại.");
                }
            } else {
                messages.add("Vui lòng nhập mật khẩu hiện tại đi kèm với mật khẩu mới và xác nhận mật khẩu.");
            }
        } else if (!userUpdateDTO.getNewPassword().equals("")) {
            messages.add("Vui lòng nhập mật khẩu hiện tại khi đổi mật khẩu.");
        }

        for (User testUser : users) {
            if (!user.getPhoneNumber().equals(userUpdateDTO.getPhoneNumber()) && testUser.getPhoneNumber().equals(userUpdateDTO.getPhoneNumber())) {
                messages.add("Số điện thoại này đã được đăng kí. Vui lòng nhập lại số điện thoại khác.");
                break;
            }
        }
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        userUpdateDTO.setBackendMessage(messages);
        if (userUpdateDTO.getBackendMessage().size() == 0) {
            userRepository.save(user);
        }
    }

    //Creator: Trương Khánh Mậu
    @Override
    public void createUser(UserRegistrationDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        Role role = new Role();
        Rate rate = new Rate();
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
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setQuestion(userDto.getQuestion());
        user.setAnswer(userDto.getAnswer());
        userRepository.save(user);

    }

    @Override
    public User checkUniqueEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> checkUniquePhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailAndIsLockedIsFalse(email);
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
    public Specification<User> getFilter(String id, String fullname, String email, String address, String rateName) {
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
            userListDTO1.setFullname(user.getFullName());
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
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    //B-Hoàng Long method
    @Override
    public Page<User> pageFindAllSearchFullName(Pageable pageable, String search) {
        return this.userRepository.findAllByAndIsLockedIsFalseAndFullNameContaining(pageable, search);
    }
    //B-Hoàng Long method
    @Override
    public User saveUser(User user) {
        this.userRepository.save(user);
        return user;
    }
}
