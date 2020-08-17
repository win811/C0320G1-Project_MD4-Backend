package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.*;
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

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone_number")
    private String phoneNumber;

    @Column(name = "user_address")
    private String address;

    @Column(name = "user_birthday")
    private LocalDate birthday;

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

    public User() {
    }

    public User(long id, String fullname, String email, String phoneNumber, String address, LocalDate birthday, String idCard, String gender, Rate rate, Long point, LocalDateTime lastLogin, Boolean status) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.idCard = idCard;
        this.gender = gender;
        this.rate = rate;
        this.point = point;
        this.lastLogin = lastLogin;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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
    private String question;

    @Column(name = "user_answer")
    private String answer;

    @Column(name = "user_reason_ban")
    private String reasonBan;

    @Column(name = "user_is_locked")
    private Boolean isLocked;
}