package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id ;

    @Column(name = "account_password")
    private String password ;

    @Column(name = "account_question")
    private String question ;

    @Column(name = "account_answer")
    private String answer ;

    @Column(name = "reason_ban")
    private String reasonBan ;

    @Column(name = "account_status")
    private Boolean isAccountLocked ;
}
