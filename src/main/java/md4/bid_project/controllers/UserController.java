package md4.bid_project.controllers;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    //B-Hoàng Long method
    @GetMapping("/users")
    public Page<User> getAllUserNotLock(@RequestParam(name = "fullName",defaultValue = "") String fullName,
                                        @PageableDefault(value = 3) Pageable pageable){
        return this.userService.getAllUserNotLock(fullName,pageable);
    }

    //B-Hoàng Long method
    @PostMapping("/user")
    public void addNewUser(@RequestBody User user){
        this.userService.addUser(user);
    }

    //B-Hoàng Long method
    @PutMapping("user/lock/{id}")
    public void lockUser(@PathVariable Long id,@RequestBody User userNeedToLock){
        User user = this.userService.findUserById(id);
        user.setIsLocked(userNeedToLock.getIsLocked());
        user.setReasonBan(userNeedToLock.getReasonBan());
        this.userService.addUser(user);
    }
}
