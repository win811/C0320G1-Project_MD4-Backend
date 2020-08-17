package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "password_reset_code")
@Data
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reset_id")
    private Long id;

    @Column(name = "reset_code")
    private String code;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "reset_status")
    private Boolean status;

}
