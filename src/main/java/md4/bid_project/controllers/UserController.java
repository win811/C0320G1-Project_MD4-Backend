package md4.bid_project.controllers;

import md4.bid_project.models.User;
import md4.bid_project.models.dto.UserListDTO;

import md4.bid_project.services.UserListDTOService;
import md4.bid_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import md4.bid_project.models.dto.UserUpdateDto;

import org.springframework.http.HttpStatus;




@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;


    //Creator: Lâm Quốc Tùng
    @Autowired
    private UserListDTOService userListDTOService;

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
    //Creator: Lâm Quốc Tùng

    @GetMapping("/user")
    public List<UserListDTO> getAllUser() {
        return userService.findAll();
    }

    @PostMapping("/user/search")
    public ResponseEntity<List<UserListDTO>> test(@RequestBody Map<String, Object> infoSearch) {
        String id = infoSearch.get("id").toString();
        String fullname = infoSearch.get("fullname").toString();
        String address = infoSearch.get("address").toString();
        String email = infoSearch.get("email").toString();
        String rate = infoSearch.get("rate").toString();

        List<UserListDTO> result = new ArrayList<>();
        if( !id.isEmpty() && !fullname.isEmpty() && !address.isEmpty() && !email.isEmpty() && !rate.isEmpty()) {
            result = userListDTOService.searchByIdAndFullNameAndEmailAndAddressAndRate(id,fullname,email,address,rate);
        } else{
            result = userListDTOService.searchByOneField(id,fullname,email,address,rate);
        }
        if(result.isEmpty()) {
            return new ResponseEntity<List<UserListDTO>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserListDTO>>(result, HttpStatus.OK);
        }
    }

}
