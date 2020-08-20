package md4.bid_project.services.impl;

import md4.bid_project.models.dto.UserListDTO;
import md4.bid_project.repositories.UserListDTORepository;
import md4.bid_project.services.UserListDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListDTOServiceImpl implements UserListDTOService {


    //Creater: Lâm Quốc Tùng
    @Autowired
    private UserListDTORepository userListDTORepository;

    @Override
    public List<UserListDTO> searchByIdAndFullNameAndEmailAndAddressAndRate(String id, String fullname, String email, String address, String rate) {
        return userListDTORepository.queryByFiveFields(id, fullname, email, address, rate);
    }

    @Override
    public List<UserListDTO> searchByOneField(String id, String fullname, String email, String address, String rate) {
        return userListDTORepository.findAllByIdContainingOrFullnameContainingOrEmailContainingOrAddressContainingOrRateContaining(id,fullname,email,address,rate);
    }
}
