package md4.bid_project.models.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String jwtToken, String email, Collection<? extends GrantedAuthority> authorities) {
        this.jwtToken = jwtToken;
        this.email = email;
        this.authorities = authorities;
    }
}


