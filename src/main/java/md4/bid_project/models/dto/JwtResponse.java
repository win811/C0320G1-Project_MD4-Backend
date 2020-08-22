package md4.bid_project.models.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private Long userId;
    private  String accountName;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String jwttoken,Long userId, String accountName,  Collection<? extends GrantedAuthority> authorities) {
        this.jwttoken = jwttoken;
        this.accountName = accountName;
        this.authorities = authorities;
        this.userId = userId;
    }
}
