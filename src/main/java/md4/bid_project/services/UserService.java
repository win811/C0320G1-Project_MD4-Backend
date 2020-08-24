package md4.bid_project.services;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserRegistrationDto;
import md4.bid_project.models.dto.UserUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import md4.bid_project.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {
//    //Creator: Nguyễn Xuân Hùng
//    UserUpdateDto findUserUpdateDtoByUserId(Long id);
//    //Creator: Nguyễn Xuân Hùng
//    User findUserById(Long id);
//    //Creator: Nguyễn Xuân Hùng
//    void updateUser(UserUpdateDto userDto);

    List<User> findAll();

    //CREATE BY ANH DUC
    User findById(Long id);

    //CREATE BY ANH DUC
    void save(User user);

    User save2(User user);

    //CREATE BY ANH DUC
    void remove(Long id);

    //CREATE BY ANH DUC
    public User findByEmail(String email);

    //CREATE BY ANH DUC
    public Optional<User> findById2(Long id);


    //CREATE BY ANH DUC
    public User findByPhoneNumber(String phoneNumber);

    //CREATE BY ANH DUC
    public void sendMail(String email, String title, String content);

    //CREATE BY ANH DUC
    public int getRandomIntegerWithinRange(int max, int min);

    //CREATE BY ANH DUC
    public boolean checkCode(String email, String code);

    //CREATE BY ANH DUC
    public boolean checlExpiryDate(String email, Date date);

    //CREATE BY ANH DUC
    public String passwordEncryption(String password);

    //CREATE BY ANH DUC
    public Boolean checkInfo(User user1, User user2);


    //Creator: Nguyễn Xuân Hùng
    UserUpdateDTO findUserUpdateDtoByUserId(Long id);

    //Creator: Nguyễn Xuân Hùng
    User findUserById(Long id);

    //Creator: Nguyễn Xuân Hùng
    void updateUser(UserUpdateDTO userDto);

    //Creator: Quốc Tùng
    Page<UserListDTO> findAll(int page);
    //Page<UserListDTO> searchFiveFields(Long id, String fullname, String email, String address, String rateName, int page);

    Page<UserListDTO> findCustomerByCriteria(Specification<User> spec, int page);

    Specification<User> getFilter(String id, String fullname, String email, String address, String rateName);

    //Creator: Trương Khánh Mậu
    void createUser(UserRegistrationDto userDto);

    //Creator: Trương Khánh Mậu
    User checkUniqueEmail(String email);

    //Create: Trương Khánh Mạu
    Optional<User> checkUniquePhone(String phoneNumber);

//    //B-Hoàng Long method
//    User findById(Long id);
    //B-Hoàng Long method
    Page<User> pageFindAllSearchFullName(Pageable pageable, String search);
    //B-Hoàng Long method
    User saveUser(User user);
}
