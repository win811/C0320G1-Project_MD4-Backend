package md4.bid_project.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
//    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
//    private Long user_id_;
    @Column(name = "user_id")
    private Long user_id_;

    @Column(name = "user_first_name")
    private String user_first_name;

    @Column(name = "user_last_name")
    private String user_last_name;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "user_phone_number")
    private String user_phone_number;

    @Column(name = "user_birthday")
    private Date user_birthday;

    @Column(name = "user_id_card")
    private String user_id_card;

    @Column(name = "user_gender")
    private String user_gender;


//    @ManyToOne(targetEntity = Rates.class)
//    @JoinColumn(name = "user_rate_id")
//    private Rates rates
    @Column(name = "user_rate_id")
    private Long user_rate_id;

    @Column(name = "user_point")
    private Long user_point;

    @Column(name = "user_last_login")
    private Date user_last_login;

    @Column(name = "user_status")
    private byte user_status;

    public User() {
    }

    public Long getUser_id_() {
        return user_id_;
    }

    public void setUser_id_(Long user_id_) {
        this.user_id_ = user_id_;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public Date getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_id_card() {
        return user_id_card;
    }

    public void setUser_id_card(String user_id_card) {
        this.user_id_card = user_id_card;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Long getUser_rate_id() {
        return user_rate_id;
    }

    public void setUser_rate_id(Long user_rate_id) {
        this.user_rate_id = user_rate_id;
    }

    public Long getUser_point() {
        return user_point;
    }

    public void setUser_point(Long user_point) {
        this.user_point = user_point;
    }

    public Date getUser_last_login() {
        return user_last_login;
    }

    public void setUser_last_login(Date user_last_login) {
        this.user_last_login = user_last_login;
    }

    public byte getUser_status() {
        return user_status;
    }

    public void setUser_status(byte user_status) {
        this.user_status = user_status;
    }
}
