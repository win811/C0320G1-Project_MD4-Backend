package md4.bid_project.services;

import md4.bid_project.models.Account;
import md4.bid_project.models.dto.AccountDto;

public interface AccountService {
    void saveAccount(AccountDto accountDto);
}
