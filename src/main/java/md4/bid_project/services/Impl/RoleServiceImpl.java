package md4.bid_project.services.Impl;

import md4.bid_project.repositories.RoleRepository;
import md4.bid_project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository ;
}
