package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.Rate;
import md4.bid_project.models.Role;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    private long id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate birthday;
    private String idCard;
    private String gender;
    private Rate rate;
    private Long point;
    private LocalDateTime lastLogin;
    private Boolean status;
    private String password ;
    private String question ;
    private String answer ;
    private Role role;
    private String reasonBan ;
    private Boolean isLocked;
}
