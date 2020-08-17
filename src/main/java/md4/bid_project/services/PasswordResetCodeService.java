package md4.bid_project.services;

import md4.bid_project.models.PasswordResetCode;
import md4.bid_project.models.User;

public interface PasswordResetCodeService {
    //CREATE BY ANH DUC
    PasswordResetCode findById(Long id);

    //CREATE BY ANH DUC
    void save(PasswordResetCode user);

    //CREATE BY ANH DUC
    void remove(Long id);

    //CREATE BY ANH DUC
    void deleteAllByUser(User user);

    //CREATE BY ANH DUC
    void deleteAll();


}
