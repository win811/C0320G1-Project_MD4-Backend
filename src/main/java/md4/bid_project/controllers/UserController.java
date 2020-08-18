package md4.bid_project.controllers;

import md4.bid_project.models.dto.UserUpdateDTO;
import md4.bid_project.models.User;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
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
}
