package md4.bid_project.models.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
//Creator: Nguyễn Xuân Hùng
@Data
public class UserUpdateDto {
    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String idCard;
    private String gender;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String phoneNumber;
    private String address;
    private List<String> backendMessage;
}
