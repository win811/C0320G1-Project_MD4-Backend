package md4.bid_project.services.impl;

import md4.bid_project.models.Role;
import md4.bid_project.models.User;
import md4.bid_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
// Creator Thien
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository ;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        System.out.println(user);
        if(user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        String email=user.getEmail() ;
        String password=user.getPassword();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Role role = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(email,password,grantedAuthorities);
    }
}
