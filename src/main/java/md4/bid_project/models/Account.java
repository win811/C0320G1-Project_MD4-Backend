package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

//@Entity
//@Table(name="accounts")
@Data
public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "account_id")
    private Long id ;

//    @Column(name = "account_password")
    private String password ;

//    @Column(name = "account_question")
    private String question ;

//    @Column(name = "account_answer")
    private String answer ;

//    @OneToOne
//    @JoinColumn(name = "account_user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "account_role_id")
    private Role role;

//    @Column(name = "account_reason_ban")
    private String reasonBan ;

//    @Column(name = "account_status")
    private Boolean isLocked;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getReasonBan() {
        return reasonBan;
    }

    public void setReasonBan(String reasonBan) {
        this.reasonBan = reasonBan;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }
}
