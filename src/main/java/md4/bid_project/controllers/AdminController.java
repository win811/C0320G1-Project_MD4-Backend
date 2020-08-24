package md4.bid_project.controllers;

import md4.bid_project.models.Account;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.models.dto.UserUpdateDto;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private UserService userService;

    //Creator: Lâm Quốc Tùng

    @GetMapping("/admin/user-list")
    public ResponseEntity<Page<UserListDTO>> getAllUser(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "fullname", defaultValue = "") String fullname,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "address", defaultValue = "") String address,
            @RequestParam(name = "rate", defaultValue = "") String rate,
            @RequestParam("page") int currentPage) {

        Page<UserListDTO> userListDTO;
        Specification<User> search = userService.getFilter(id, fullname, email, address, rate);
        if(search != null) {
            userListDTO = userService.findCustomerByCriteria(search, currentPage);
        }
        else {
            userListDTO = userService.findAll(currentPage);
        }
        return ResponseEntity.ok(userListDTO);
    }

    @PostMapping("/admin/user-create")
    public void createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        userService.saveUser(user);
    }

    @PutMapping("/admin/user-edit/{id}")
    public ResponseEntity<UserListDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserListDTO userListDTO) {
        User user = userService.findUserById(id);
        if(user==null){
            return new ResponseEntity<UserListDTO>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(userListDTO);
        return new ResponseEntity<UserListDTO>(userListDTO,HttpStatus.OK);
    }
}