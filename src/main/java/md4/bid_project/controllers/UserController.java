package md4.bid_project.controllers;

import md4.bid_project.models.Account;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.AccountDTO;
import md4.bid_project.models.dto.JwtResponse;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.security.JwtTokenUtil;
import md4.bid_project.services.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
        System.out.println(accountDTO.getEmail());
        System.out.println(accountDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        User user = userRepository.findByEmail(accountDTO.getEmail());
        System.out.println(userDetails);

        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, user.getId(),userDetails.getUsername(), userDetails.getAuthorities()));
    }
}
