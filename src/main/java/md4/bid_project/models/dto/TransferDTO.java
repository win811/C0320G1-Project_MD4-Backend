package md4.bid_project.models.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private Long userId;
    private String nonce;
    private String deliveryMethod;
}
