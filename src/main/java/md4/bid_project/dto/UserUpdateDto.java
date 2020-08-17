package md4.bid_project.dto;

import lombok.Data;
import md4.bid_project.models.DeliveryAddress;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
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
