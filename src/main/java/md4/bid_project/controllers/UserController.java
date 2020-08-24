package md4.bid_project.controllers;

import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.models.User;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //B-Hoàng Long method
    @GetMapping(value = "/user/lock",  params = {"page", "size", "search"})
    public ResponseEntity<Page<User>> getAllUserNotLock(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("search") String search) {
        Page<User> users = this.userService.pageFindAllSearchFullName(PageRequest.of(page, size), search);
        if (users.isEmpty()) {
            return new ResponseEntity<>(users,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    //B-Hoàng Long method
    @PostMapping("/user")
    public User addNewUser(@RequestBody User user){
        return this.userService.saveUser(user);
    }

    //B-Hoàng Long method
    @PutMapping("/user/lock/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user) {
        User userLock = userService.findById(id);
        if (userLock == null) {
            return new ResponseEntity<User>(userLock, HttpStatus.NO_CONTENT);
        }
        userLock.setIsLocked(user.getIsLocked());
        userLock.setReasonBan(user.getReasonBan());
        this.userService.saveUser(userLock);
        return new ResponseEntity<User>(userLock, HttpStatus.OK);
    }
}
