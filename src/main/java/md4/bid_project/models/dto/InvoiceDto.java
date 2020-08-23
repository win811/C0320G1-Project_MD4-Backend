package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.CartDetail;
import md4.bid_project.models.Order;

import java.util.List;

//Creator: Nguyễn Xuân Hùng
@Data
public class InvoiceDto {
    private Order order;
    private List<CartDetail> cartDetail;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<CartDetail> getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(List<CartDetail> cartDetail) {
        this.cartDetail = cartDetail;
    }
}
