package md4.bid_project.controllers;

import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.User;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping("/user")
        public List<User> getAllUser() {
            return userService.findAll();
        }

        //Tùng
        @GetMapping("/user/{id}")
        public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
                throws ResourceNotFoundException {
            User user = userService.findById(userId);
            return ResponseEntity.ok().body(user);
        }

        //Tùng
        @PostMapping("/user")
        public User createUser(@Valid @RequestBody User user) {
            return userService.save(user);
        }

        //Tùng
        @PutMapping("/user/{id}")
        public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                                       @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
            User user = userService.findById(userId);

            user.setId(userDetails.getId());
            user.setFullname(userDetails.getFullname());
            user.setEmail(userDetails.getEmail());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            user.setAddress(userDetails.getAddress());
            user.setBirthday(userDetails.getBirthday());
            user.setIdCard(userDetails.getIdCard());
            user.setGender(userDetails.getGender());
            user.setRate(userDetails.getRate());
            user.setPoint(userDetails.getPoint());
            user.setLastLogin(userDetails.getLastLogin());
            user.setStatus(userDetails.getStatus());

            final User updatedUser = userService.save(user);
            return ResponseEntity.ok(updatedUser);
        }

        //Tùng
        @DeleteMapping("/user/{id}")
        public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
                throws ResourceNotFoundException {
            User user = userService.findById(userId);

            userService.removeById(userId);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
}
