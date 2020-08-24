package md4.bid_project.controllers;


import md4.bid_project.models.User;
import md4.bid_project.models.dto.AccountDTO;
import md4.bid_project.models.dto.JwtResponse;
import md4.bid_project.models.dto.UserRegistrationDto;
import md4.bid_project.models.dto.UserUpdateDTO;
import md4.bid_project.repositories.UserRepository;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
// Creator Thien
public class UserController {

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user/register")
    public ResponseEntity<UserRegistrationDto> registration(@RequestBody UserRegistrationDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user/checkEmail")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody String email) {
        Map<String, Object> result = new LinkedHashMap<>();
        User user = userRepository.findByEmail(email);
        if (user != null) {
            result.put("userId", user.getId());
            result.put("message", "Email này đã được đăng kí.");
            return ResponseEntity.ok(result);
        } else {
            result.put("userId", -1);
            result.put("message", "Email này chưa được đăng kí.");
            return ResponseEntity.ok(result);
        }
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user/checkPhone")
    public ResponseEntity<Map<String, Object>> checkPhone(@RequestBody String phoneNumber) {
        Map<String, Object> result = new LinkedHashMap<>();
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            result.put("userId", user.get().getId());
            result.put("message", "SDT này đã được đăng kí.");
            return ResponseEntity.ok(result);
        } else {
            result.put("userId", -1);
            result.put("message", "SDT này chưa được đăng kí.");
            return ResponseEntity.ok(result);
        }
    }

    //Creator: Nguyễn Xuân Hùng
    @GetMapping("/user/{id}")
    public ResponseEntity<UserUpdateDTO> findUserById(@PathVariable Long id) {
        UserUpdateDTO userUpdateDTO = userService.findUserUpdateDtoByUserId(id);
        if (userUpdateDTO == null) {
            return new ResponseEntity<UserUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userUpdateDTO);
    }

    //Creator: Nguyễn Xuân Hùng
    @PutMapping("user/update/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<UserUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(userUpdateDTO);
        return new ResponseEntity<UserUpdateDTO>(userUpdateDTO, HttpStatus.OK);
    }

    // Creater Thien
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        User user = userService.findByEmail(userDetails.getUsername());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, user.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
