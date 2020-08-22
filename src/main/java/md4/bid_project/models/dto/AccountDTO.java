package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.Role;

import java.io.Serializable;


@Data
public class AccountDTO implements Serializable {
    private long id;
    private String password ;
    private String email;
    private Role role;
    private String reasonBan ;
    private Boolean isLocked;

}
