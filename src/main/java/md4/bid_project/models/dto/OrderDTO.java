package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.User;

//creator: Đặng Hồng Quân team C
@Data
public class OrderDTO {
    private User buyer;
    private String paymentMethod;
    private String deliveryMethod;
    private String deliveryAddress;
    private String paymentState;
}