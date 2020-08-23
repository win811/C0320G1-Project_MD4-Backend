package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;

import java.util.List;

//Creator: Nguyễn Xuân Hùng
@Data
public class InvoiceDTO {
    private Order order;
    private List<CartDetail> cartDetail;
}
