package md4.bid_project.services;

import md4.bid_project.models.dto.UserListDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserListDTOService {

    //Creater: Lâm Quốc Tùng
    public List<UserListDTO> searchByIdAndFullNameAndEmailAndAddressAndRate(String id, String fullname, String email, String address, String rate);
    public List<UserListDTO> searchByOneField(String id, String fullname, String email, String address, String rate);
}
