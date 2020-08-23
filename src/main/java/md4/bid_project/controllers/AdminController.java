package md4.bid_project.controllers;

import md4.bid_project.models.Account;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private UserService userService;

    //Creator: Lâm Quốc Tùng

    @GetMapping("admin/user-list")
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
}