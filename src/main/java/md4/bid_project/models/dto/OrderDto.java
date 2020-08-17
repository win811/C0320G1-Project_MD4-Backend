package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.DeliveryAddress;
import md4.bid_project.models.User;

//creator: Đặng Hồng Quân team C
@Data
public class OrderDto {
    private User buyer;
    private String paymentMethod;
    private String deliveryMethod;
    private DeliveryAddress deliveryAddress;
    private String paymentState;
}
