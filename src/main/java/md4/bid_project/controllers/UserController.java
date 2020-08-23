package md4.bid_project.controllers;

import md4.bid_project.models.dto.UserRegistrationDto;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import md4.bid_project.models.dto.JwtResponse;
import md4.bid_project.models.dto.AccountDTO;
import md4.bid_project.security.JwtTokenUtil;
import md4.bid_project.services.impl.UserDetailServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

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
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user")
    public ResponseEntity<UserRegistrationDto> registration(@RequestBody UserRegistrationDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user/checkEmail")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody String email) {
        Map<String, Object> result = new LinkedHashMap<>();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            result.put("userId", user.get().getId());
            result.put("message", "Email này đã được đăng kí.");
            return ResponseEntity.ok(result);
        }else {
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
        }else {
            result.put("userId", -1);
            result.put("message", "SDT này chưa được đăng kí.");
            return ResponseEntity.ok(result);
        }
    }

    //Creator: Nguyễn Xuân Hùng
    @GetMapping("/user/{id}")
    public ResponseEntity<UserUpdateDto> findUserById(@PathVariable Long id){
        UserUpdateDto userDto = userService.findUserUpdateDtoByUserId(id);
        if(userDto==null){
            System.out.println("user "+id+" not found in the database");
            return new ResponseEntity<UserUpdateDto>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }

    //Creator: Nguyễn Xuân Hùng
    @PutMapping("user/update/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userDto){
        User user = userService.findUserById(id);
        if(user==null){
            return new ResponseEntity<UserUpdateDto>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(userDto);
        return new ResponseEntity<UserUpdateDto>(userDto,HttpStatus.OK);
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
        return ResponseEntity.ok(new JwtResponse(jwtToken,user.getId(), userDetails.getUsername(), userDetails.getAuthorities()));

    }

}
