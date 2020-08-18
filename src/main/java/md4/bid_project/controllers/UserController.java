package md4.bid_project.controllers;

import md4.bid_project.models.dto.JwtResponse;
import md4.bid_project.models.dto.UserDTO;
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
@RequestMapping("/api")
// Creator Thien
public class UserController {
    @Autowired(required = false)
     AuthenticationManager authenticationManager;
    @Autowired
     JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    UserDetailServiceImpl userDetailServiceImpl;
    // Creater Thien
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        System.out.println(userDetails.getUsername());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
