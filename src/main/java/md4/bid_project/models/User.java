package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_fullname")
    private String fullname;

    @Pattern(regexp = "^([-\\w.])+[a-zA-Z\\d]@(\\w+\\.)+(\\w+)$", message = "{regex.email}")
    @NotNull(message = "{NotNull.email}")
    @Column(name = "user_email")
    private String email;

    @NotNull(message = "{NotNull.phoneNumber}")
    @Pattern(regexp = "^(\\+84|0)+([0-9]{9})\\b$", message = "{regex.phoneNumber}")
    @Column(name = "user_phone_number")
    private String phoneNumber;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_birthday")
    private LocalDate birthday;

    @Pattern(regexp = "^\\d{9}|\\d{12}$", message = "{regex.idCard}")
    @NotNull(message = "{NotNull.idCard}")
    @Column(name = "user_id_card")
    private String idCard;

    @Column(name = "user_gender")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "user_rate_id")
    private Rate rate;

    @Column(name = "user_point")
    private Long point;

    @Column(name = "user_last_login")
    private LocalDateTime lastLogin;

    @Column(name = "user_status")
    private Boolean status;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = "user")
    private List<DeliveryAddress> deliveryAddressList;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private Role role;

    @OneToOne(mappedBy = "user")
    private PasswordResetCode passwordResetCode;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_question")
    @NotNull(message = "{NotNull.question}")
    private String question;

    @NotNull(message = "{NotNull.answer}")
    @Column(name = "user_answer")
    private String answer;

    @Column(name = "user_reason_ban")
    private String reasonBan;

    @Column(name = "user_is_locked")
    private Boolean isLocked;


    @JsonIgnore
    public PasswordResetCode getPasswordResetCode() {
        return passwordResetCode;
    }

    @JsonProperty
    public void setPasswordResetCode(PasswordResetCode passwordResetCode) {
        this.passwordResetCode = passwordResetCode;
    }
}