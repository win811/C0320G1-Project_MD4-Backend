package md4.bid_project.services.Impl;
import md4.bid_project.repositories.AccountRepository;
import md4.bid_project.repositories.UserRepository;

import md4.bid_project.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

}
