package md4.bid_project.controllers;

import md4.bid_project.models.dto.JwtResponse;
import md4.bid_project.models.dto.UserDTO;
import md4.bid_project.security.JwtTokenUtil;
import md4.bid_project.services.UserService;
import md4.bid_project.services.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class UserController {
    @Autowired(required = false)
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    private UserDetailService userDetailServiceImpl;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
