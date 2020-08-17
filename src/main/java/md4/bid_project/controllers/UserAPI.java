package md4.bid_project.controllers;

import md4.bid_project.models.PasswordResetCode;
import md4.bid_project.models.User;
import md4.bid_project.services.PasswordResetCodeService;
import md4.bid_project.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CREATE BY ANH DUC
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetCodeService passwordResetCodeService;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAllUser() {

        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        System.out.println("User With Id :" + id);
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/recover/{email}")
    public ResponseEntity<Void> sendMailRecover(@PathVariable("email") String email) {
        User currentUser = userService.findByEmail(email);
        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        passwordResetCodeService.deleteAllByUser(currentUser);
        String title;
        String content;
        int code;
        code = userService.getRandomIntegerWithinRange(999999, 100000);
        java.util.Date timeNow = new java.util.Date();
        PasswordResetCode passwordResetCode = new PasswordResetCode();
        passwordResetCode.setUser(currentUser);
        passwordResetCode.setCode(String.valueOf(code));
        passwordResetCode.setExpiryDate(timeNow);
        passwordResetCodeService.save(passwordResetCode);
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

        System.out.println("Done");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("recover/{email}/{code}")
    public ResponseEntity<Void> checkCodeRecover(@PathVariable("email") String email, @PathVariable("code") String code) {
        String title;
        String content;
        String newPassword;
        String passwordEncryption;
        User currentUser = userService.findByEmail(email);
        java.util.Date date = new java.util.Date();

        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if (!userService.checlExpiryDate(email, date)) {
            System.out.println("Quá thời gian");
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

        }
        if (!currentUser.getPasswordResetCode().getCode().equals(code)) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        newPassword = RandomStringUtils.randomAlphanumeric(10);
        passwordEncryption = userService.passwordEncryption(newPassword);
        currentUser.setPassword(passwordEncryption);
        userService.save(currentUser);
        passwordResetCodeService.remove(currentUser.getPasswordResetCode().getId());
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

        System.out.println("Done");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/recover")
    public ResponseEntity<Void> checkInfoRecover(@RequestBody User user) {
        String title;
        String content;
        String newPassword;
        String passwordEncryption;
        User currentUser = userService.findByEmail(user.getEmail());

        if (currentUser == null) {
            System.out.println("User with email " + user.getEmail() + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if (!userService.checkInfo(currentUser, user)) {
            System.out.println("Sai Info");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        newPassword = RandomStringUtils.randomAlphanumeric(10);
        passwordEncryption = userService.passwordEncryption(newPassword);
        System.out.println("checkok");
        currentUser.setPassword(passwordEncryption);
        userService.save(currentUser);
        System.out.println("savepassok");
        title = "Yêu Cầu Thay Đổi Mật Khẩu";
        content = "Chào quý khách,\n" +
                "Mật Khẩu của quý khách đã được thay đổi thành công.\n" +
                "\n" +
                "Mật Khẩu Mới của quý khách là : " + newPassword;
        System.out.println("Sending Email...");
        try {

            userService.sendMail(user.getEmail(), title, content);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
