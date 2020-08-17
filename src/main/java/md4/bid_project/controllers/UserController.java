package md4.bid_project.controllers;

import md4.bid_project.models.Account;
import md4.bid_project.models.dto.AccountDto;
import md4.bid_project.repositories.AccountRepository;
import md4.bid_project.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/users")
    public List<Account> getAllEmployees() {
        return accountRepository.findAll();
    }

    @PostMapping("users")
    public ResponseEntity<AccountDto> registration(@RequestBody AccountDto accountDto) {
        accountService.saveAccount(accountDto);
        return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
    }
}