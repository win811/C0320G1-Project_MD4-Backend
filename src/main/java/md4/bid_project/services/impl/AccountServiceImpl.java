package md4.bid_project.services.impl;

import md4.bid_project.models.Account;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.AccountDto;
import md4.bid_project.repositories.AccountRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void saveAccount(AccountDto accountDto) {
        User user = userRepository.findById(accountDto.getId()).orElse(null);
        Account account = accountRepository.findAccountByUserId(accountDto.getId());
        assert account != null;
        account.setPassword(accountDto.getPassword());
        account.setQuestion(accountDto.getQuestion());
        account.setAnswer(accountDto.getAnswer());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setFullname(encoder.encode(accountDto.getFullName()));
        user.setAddress(encoder.encode(accountDto.getAddress()));
        user.setGender(encoder.encode(accountDto.getGender()));
        user.setPhoneNumber(encoder.encode(accountDto.getPhoneNumber()));
        user.setBirthday(accountDto.getBirthday());
        user.setIdCard(accountDto.getIdCard());
        user.setEmail(encoder.encode(accountDto.getEmail()));
        accountRepository.save(account);

    }
}
