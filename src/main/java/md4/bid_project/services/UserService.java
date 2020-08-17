package md4.bid_project.services;

import md4.bid_project.models.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> findAll();
    //CREATE BY ANH DUC
    User findById(Long id);

    //CREATE BY ANH DUC
    void save(User user);

    //CREATE BY ANH DUC
    void remove(Long id);

    //CREATE BY ANH DUC
    public User findByEmail(String email);

    //    CREATE BY ANH DUC
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


}
