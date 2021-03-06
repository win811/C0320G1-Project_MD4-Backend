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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<?> registration(@RequestBody UserRegistrationDto userDto) {
        userService.createUser(userDto);
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
////        );
//        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(u.getName());
//        User user = userService.findByEmail(userDetails.getUsername());
//        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user/checkEmail")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody String email) {
        Map<String, Object> result = new LinkedHashMap<>();
        User user = userRepository.findByEmail(email);
        if (user != null) {
            result.put("userId", user.getId());
            return ResponseEntity.ok(result);
        } else {
            result.put("userId", -1);
            return ResponseEntity.ok(result);
        }
    }

    //Creator: Trương Khánh Mậu
    @PostMapping("/user/checkPhone")
    public ResponseEntity<Map<String, Object>> checkPhone(@RequestBody String phoneNumber) {
        Map<String, Object> result = new LinkedHashMap<>();
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            result.put("userId", user.getId());
            return ResponseEntity.ok(result);
        } else {
            result.put("userId", -1);
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

    //B-Hoàng Long method
    @GetMapping("/user/long/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //B-Hoàng Long method
    @GetMapping(value = "/user/long/lock", params = {"page", "size", "search"})
    public ResponseEntity<Page<User>> getAllUserNotLock(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("search") String search) {
        Page<User> users = this.userService.pageFindAllSearchFullName(PageRequest.of(page, size), search);
        if (users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //B-Hoàng Long method
    @PostMapping("/user/long")
    public User addNewUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    //B-Hoàng Long method
    @PutMapping("/user/long/lock/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User userLock = userService.findById(id);
        if (userLock == null) {
            return new ResponseEntity<User>(userLock, HttpStatus.NO_CONTENT);
        }
        userLock.setIsLocked(user.getIsLocked());
        userLock.setReasonBan(user.getReasonBan());
        this.userService.saveUser(userLock);
        return new ResponseEntity<User>(userLock, HttpStatus.OK);
    }
    // Created by : Bách
//    @GetMapping("user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        if (user == null) {
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }
}
