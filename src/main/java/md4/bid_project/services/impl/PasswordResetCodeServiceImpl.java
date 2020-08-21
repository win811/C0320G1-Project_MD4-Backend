package md4.bid_project.services.impl;

import md4.bid_project.models.PasswordResetCode;
import md4.bid_project.models.User;
import md4.bid_project.repositories.PasswordResetCodeRepository;
import md4.bid_project.services.PasswordResetCodeService;
import md4.bid_project.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PasswordResetCodeServiceImpl implements PasswordResetCodeService {
    @Autowired
    private PasswordResetCodeRepository passwordResetCodeRepository;
    @Autowired
    private UserService userService;

    //CREATE BY ANH DUC
    @Override
    public PasswordResetCode findById(Long id) {
        return passwordResetCodeRepository.findById(id).orElse(null);
    }

    //CREATE BY ANH DUC
    @Override
    public void save(PasswordResetCode passwordResetCode) {
        passwordResetCodeRepository.save(passwordResetCode);
    }

    //CREATE BY ANH DUC
    @Override
    public void remove(Long id) {
        passwordResetCodeRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUser(User user) {
        passwordResetCodeRepository.deleteAllByUser(user);
    }

    @Override
    public void deleteAll() {
        passwordResetCodeRepository.deleteAll();
    }

    @Override
    public void createConfirmCode(String email) {
        int code;
        String title;
        String content;
        User currentUser = userService.findByEmail(email);
        deleteAllByUser(currentUser);
        code = userService.getRandomIntegerWithinRange(999999, 100000);
        java.util.Date timeNow = new java.util.Date();
        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setUser(currentUser);
        passwordResetCode.setCode(String.valueOf(code));
        passwordResetCode.setExpiryDate(timeNow);
        save(passwordResetCode);
        System.out.println(passwordResetCode.getCode());

        title = "Yêu Cầu Thay Đổi Mật Khẩu";
        content = "Chào quý khách,\n" +
                "webdaugiac03.vn đã nhận được yêu cầu thay đổi mật khẩu của quý khách.\n" +
                "\n" +
                "Mã xác thực của quý khách là : " + code;
        System.out.println("Sending Email...");
        try {

            userService.sendMail(email, title, content);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void resetPassword(String email) {
        String title;
        String content;
        String newPassword;
        String passwordEncryption;
        User currentUser = userService.findByEmail(email);
        newPassword = RandomStringUtils.randomAlphanumeric(10);
        passwordEncryption = userService.passwordEncryption(newPassword);
        currentUser.setPassword(passwordEncryption);
        System.out.println(passwordEncryption);
        userService.save(currentUser);
        remove(currentUser.getPasswordResetCode().getId());
        System.out.println("save ok");
        title = "Yêu Cầu Thay Đổi Mật Khẩu";
        content = "Chào quý khách,\n" +
                "Mật Khẩu của quý khách đã được thay đổi thành công.\n" +
                "\n" +
                "Mật Khẩu Mới của quý khách là : " + newPassword;
        System.out.println("Sending Email...");
        try {

            userService.sendMail(email, title, content);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
