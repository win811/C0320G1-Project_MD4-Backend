package md4.bid_project.controllers;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.AccountDTO;
import md4.bid_project.models.dto.JwtResponse;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.security.JwtTokenUtil;
import md4.bid_project.services.UserService;
import md4.bid_project.services.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
// Creator Thien
public class UserController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    UserService userService;

    //Creator: Nguyễn Xuân Hùng
    @GetMapping("/user/{id}")
    public ResponseEntity<UserUpdateDto> findUserById(@PathVariable Long id) {
        UserUpdateDto userDto = userService.findUserUpdateDtoByUserId(id);
        if (userDto == null) {
            System.out.println("user " + id + " not found in the database");
            return new ResponseEntity<UserUpdateDto>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }

    //Creator: Nguyễn Xuân Hùng
    @PutMapping("user/update/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<UserUpdateDto>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(userDto);
        return new ResponseEntity<UserUpdateDto>(userDto, HttpStatus.OK);
    }

    // Creater Thien
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        User user = userService.findByEmail(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtToken, user.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
