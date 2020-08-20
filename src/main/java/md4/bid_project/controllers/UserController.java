package md4.bid_project.controllers;

import md4.bid_project.models.dto.UserUpdateDTO;
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
    public ResponseEntity<UserUpdateDTO> findUserById(@PathVariable Long id){
        UserUpdateDTO userUpdateDTO = userService.findUserUpdateDtoByUserId(id);
        if(userUpdateDTO==null){
            return new ResponseEntity<UserUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userUpdateDTO);
    }
    //Creator: Nguyễn Xuân Hùng
    @PutMapping("user/update/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO){
        User user = userService.findUserById(id);
        if(user==null){
            return new ResponseEntity<UserUpdateDTO>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(userUpdateDTO);
        return new ResponseEntity<UserUpdateDTO>(userUpdateDTO,HttpStatus.OK);
    }

    // Creater Thien
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO) {
        System.out.println(accountDTO.getEmail());
        System.out.println(accountDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword())
        );
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(authentication.getName());
        System.out.println(userDetails.getUsername());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
