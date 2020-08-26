package md4.bid_project.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CartDetailDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long auctionId;

    @NotNull
    private Double winPrice;

    @NotNull
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeTime;
}
