package md4.bid_project.models.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String gender;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;
    private String idCard;
    private String address;
    private String question;
    private String answer;
    private String password;
    private List<String> notificationEmail;
    private List<String> notificationPhoneNumber;
}
