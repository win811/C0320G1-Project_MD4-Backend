package md4.bid_project.models.dto;

import lombok.Data;

import java.util.List;

//all created by Thao
@Data
public class DealManageApi {
    private int currentPage;
    private int totalPage;
    private int totalItems;
    private List<DealManageDTO> items;
}
