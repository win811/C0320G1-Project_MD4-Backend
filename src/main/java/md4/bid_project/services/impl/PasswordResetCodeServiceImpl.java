package md4.bid_project.services.impl;

import md4.bid_project.models.PasswordResetCode;
import md4.bid_project.models.User;
import md4.bid_project.repositories.PasswordResetCodeRepository;
import md4.bid_project.services.PasswordResetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PasswordResetCodeServiceImpl implements PasswordResetCodeService {
    @Autowired
    private PasswordResetCodeRepository passwordResetCodeRepository;
    //CREATE BY ANH DUC
    @Override
    public PasswordResetCode findById(Long id) {
        return passwordResetCodeRepository.findById(id).orElse(null);
    }
    //CREATE BY ANH DUC
    @Override
    public void save(PasswordResetCode passwordResetCode) {
        passwordResetCodeRepository.save(passwordResetCode);
    }
    //CREATE BY ANH DUC
    @Override
    public void remove(Long id) {
        passwordResetCodeRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUser(User user) {
        passwordResetCodeRepository.deleteAllByUser(user);
    }
    @Override
    public void deleteAll() {
        passwordResetCodeRepository.deleteAll();
    }

}
