package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.Role;

import java.io.Serializable;

@Data
public class AccountDTO implements Serializable {
    private long id;
    private String password ;
    private String email ;
    private Role role;
    private String reasonBan ;
    private Boolean isLocked;

    public AccountDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
