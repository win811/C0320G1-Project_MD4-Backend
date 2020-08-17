package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.dto.DealManageDTO;

import java.util.List;

//all created by Thao
@Data
public class DealManageApi {
    private int currentPage;
    private int totalPage;
    private List<DealManageDTO> items;
}
