package md4.bid_project.controllers;

import md4.bid_project.dto.UserDto;
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
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        UserDto userDto = userService.findUserDtoByUserId(id);
        if(userDto==null){
            System.out.println("user "+id+" not found in the database");
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }
    @PutMapping("user/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto userDto){
        User user = userService.findUserById(id);
        if(user==null){
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(userDto);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
    }
}
