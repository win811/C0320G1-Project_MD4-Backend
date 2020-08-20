package md4.bid_project.controllers;

import md4.bid_project.exception.ErrorResponse;
import md4.bid_project.exception.NotFoundException;
import md4.bid_project.models.PasswordResetCode;
import md4.bid_project.models.User;
import md4.bid_project.services.PasswordResetCodeService;
import md4.bid_project.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

//CREATE BY ANH DUC
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Validated
public class UserAPI {
    @Autowired
    private UserService userService;
    //CREATE BY ANH DUC
    @Autowired
    private PasswordResetCodeService passwordResetCodeService;

    //CREATE BY ANH DUC, Hàm lấy thông tin tất cả User
    @GetMapping("/users")
    public ResponseEntity<? extends Object> listAllUser() {
        List<User> users = userService.findAll();
        ErrorResponse response = new ErrorResponse();
        if (users.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Data Null");
            return new ResponseEntity<Object>(response, response.getStatus());
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Get data Thành Công");
        response.setBody(users);
        return new ResponseEntity<Object>(response, response.getStatus());

    }

    //CREATE BY ANH DUC, hàm get User theo Id
    @GetMapping("/users/{id}")
    public ResponseEntity<? extends Object> getUserById(@PathVariable("id") Long id) {
        System.out.println("User With Id :" + id);
        ErrorResponse response = new ErrorResponse();
        User user = userService.findById(id);
        if (user == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User Id :" + id + " không tồn tại!");
            return new ResponseEntity<Object>(response, response.getStatus());
        }

        response.setStatus(HttpStatus.OK);
        response.setMessage("Get data Thành Công");
        response.setBody(user);
        return new ResponseEntity<Object>(response, response.getStatus());
    }

    //CREATE BY ANH DUC, Hàm nhận và gửi mã xác nhận
    @GetMapping("/recover/{email}")
    public ResponseEntity<ErrorResponse> sendMailRecover(@PathVariable("email") String email) {
        ErrorResponse response = new ErrorResponse();
        User currentUser = userService.findByEmail(email);
        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User with email " + email + " not found");
            return new ResponseEntity<ErrorResponse>(response, response.getStatus());
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
        response.setStatus(HttpStatus.OK);
        response.setMessage("Mã xác minh đã được gửi đến email của bạn, vui lòng kiểm tra lại email trước khi thử lại!");
        System.out.println("Done");
        return new ResponseEntity<ErrorResponse>(response, response.getStatus());
    }

    //CREATE BY ANH DUC, Hàm xử lí resetPassword
    @GetMapping("recover/{email}/{code}")
    public ResponseEntity<ErrorResponse> checkCodeRecover(@PathVariable("email") String email, @PathVariable("code") String code) throws IOException {
        ErrorResponse response = new ErrorResponse();
        String title;
        String content;
        String newPassword;
        String passwordEncryption;
        User currentUser = userService.findByEmail(email);
        java.util.Date date = new java.util.Date();

        if (currentUser == null) {
            System.out.println("User with email " + email + " not found");
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User with email " + email + " not found");
            return new ResponseEntity<ErrorResponse>(response, response.getStatus());
        }
        if (!currentUser.getPasswordResetCode().getCode().equals(code)) {
            System.out.println("Code k chính xác");
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Mã xác thực không chính xác!");
            return new ResponseEntity<ErrorResponse>(response, response.getStatus());
        }
        if (!userService.checlExpiryDate(email, date)) {
            System.out.println("Quá thời gian");
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Mã xác minh đã hết hạn, vui lòng lấy mã mới!");
            return new ResponseEntity<ErrorResponse>(response, response.getStatus());

        }
        newPassword = RandomStringUtils.randomAlphanumeric(10);
        passwordEncryption = userService.passwordEncryption(newPassword);
        currentUser.setPassword(passwordEncryption);
        System.out.println(passwordEncryption);
        userService.save(currentUser);
        passwordResetCodeService.remove(currentUser.getPasswordResetCode().getId());
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

        System.out.println("Done");
        response.setStatus(HttpStatus.OK);
        response.setMessage("Thay đổi mật khẩu thành công, mật khẩu mới đã được gửi về email của bạn!");
        return new ResponseEntity<ErrorResponse>(response, response.getStatus());
    }

    //CREATE BY ANH DUC, hàm nhận và xử lí resetPassword bằng câu hỏi bảo mật +info
    @PostMapping("/recover")
    public ResponseEntity<ErrorResponse> checkInfoRecover(@RequestBody User user) {
        String title;
        String content;
        String newPassword;
        String passwordEncryption;
        User currentUser = userService.findByEmail(user.getEmail());
        ErrorResponse response = new ErrorResponse();

        if (currentUser == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User with email " + user.getEmail() + " not found");
            return new ResponseEntity<ErrorResponse>(response, response.getStatus());
        }

        if (!userService.checkInfo(currentUser, user)) {
            System.out.println("Sai Info");
            response.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
            response.setMessage("Thông tin xác thực không chính xác, vui lòng kiểm tra lại!");
            return new ResponseEntity<ErrorResponse>(response, response.getStatus());
        }
        System.out.println("return thành công");
        newPassword = RandomStringUtils.randomAlphanumeric(10);
        passwordEncryption = userService.passwordEncryption(newPassword);
        System.out.println("check ok");
        currentUser.setPassword(passwordEncryption);
        System.out.println("setPass oK");
        userService.save(currentUser);
        System.out.println("savepass ok");
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
        response.setStatus(HttpStatus.OK);
        response.setMessage("Thay đổi mật khẩu thành công, mật khẩu mới đã được gửi về email của bạn!");
        return new ResponseEntity<ErrorResponse>(response, response.getStatus());
    }

}
