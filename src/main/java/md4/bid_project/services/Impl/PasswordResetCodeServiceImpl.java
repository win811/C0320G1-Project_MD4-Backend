package md4.bid_project.services.Impl;

import md4.bid_project.repositories.PasswordResetCodeRepository;
import md4.bid_project.services.PasswordResetCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetCodeServiceImpl implements PasswordResetCodeService {
    @Autowired
    private PasswordResetCodeRepository passwordResetCodeRepository;
}
