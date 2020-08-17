package md4.bid_project.controllers;


import md4.bid_project.models.User;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public List<User> getUserList() {
        return userService.getAllUser();
    }

    @PostMapping("/user-create")
//    public void createUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder) {
        userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        Optional<User> currentUser = userService.findById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentUser.get().setId(user.getId());
        currentUser.get().setFullname(user.getFullname());
        currentUser.get().setAddress(user.getAddress());
        currentUser.get().setRate(user.getRate());
        currentUser.get().setEmail(user.getEmail());
        currentUser.get().setPhoneNumber(user.getPhoneNumber());
        currentUser.get().setLastLogin(user.getLastLogin());
        currentUser.get().setPoint(user.getPoint());
        userService.saveUser(currentUser.get());
        return new ResponseEntity<>(currentUser.get(),HttpStatus.OK);
    }
}

