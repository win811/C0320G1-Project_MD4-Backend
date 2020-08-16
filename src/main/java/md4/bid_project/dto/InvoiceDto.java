package md4.bid_project.dto;

import lombok.Data;
import md4.bid_project.models.Cart;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;
import md4.bid_project.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {
    private Order order;
    private List<CartDetail> cartDetail;
}
