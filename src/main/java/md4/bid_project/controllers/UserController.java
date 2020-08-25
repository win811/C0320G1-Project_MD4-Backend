package md4.bid_project.controllers;

import md4.bid_project.models.User;
import md4.bid_project.services.UserService;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = userService.findAll();
        if(users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById (@PathVariable("id") Long id) {
        User user = userService.finById(id);
        if(user == null) {
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(user);
    }
}
