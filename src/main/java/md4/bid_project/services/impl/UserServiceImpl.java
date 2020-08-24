package md4.bid_project.services.impl;

import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.repositories.DeliveryAddressRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.UserService;
import md4.bid_project.services.search.SearchCriteria;
import md4.bid_project.services.search.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
    @Override
    public void updateUser(UserListDTO userListDto) {
        User user = userRepository.findById(userListDto.getId()).orElse(null);
        assert user != null;
        user.setFullname(userListDto.getFullName().trim());
        user.setAddress(userListDto.getAddress().trim());
        user.setGender(userListDto.getGender());
        user.setPhoneNumber(userListDto.getPhoneNumber());
        user.setIdCard(userListDto.getIdCard());
        user.setBirthday(userListDto.getBirthday());
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
//Creator Nguyễn Hữu Hậu
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }



//    @PutMapping("/user-edit/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
//        Optional<User> currentUser = userRepository.findById(id);
//        if (!currentUser.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        currentUser.get().setId(user.getId());
//        currentUser.get().setFullname(user.getFullname());
//        currentUser.get().setAddress(user.getAddress());
//        currentUser.get().setRate(user.getRate());
//        currentUser.get().setEmail(user.getEmail());
//        currentUser.get().setPhoneNumber(user.getPhoneNumber());
//        currentUser.get().setLastLogin(user.getLastLogin());
//        currentUser.get().setPoint(user.getPoint());
//        userRepository.saveUser(currentUser.get());
//        return new ResponseEntity<>(currentUser.get(),HttpStatus.OK);
//    }

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
        return new PageImpl<UserListDTO>(userListDTO);
    }

}