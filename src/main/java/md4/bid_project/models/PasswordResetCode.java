package md4.bid_project.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
//CREATE BY ANH DUC
@Entity
@Table(name = "password_reset_code")
@Data
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reset_id")
    private Long id;

    @Min(100000)@Max(999999)
    @Column(name = "reset_code")
    private String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    //CREATE BY ANH DUC
    @Column(name = "reset_status")
    private Boolean status;

    //CREATE BY ANH DUC
    @Column(name = "reset_expiry_date")
    private Date expiryDate;

}
