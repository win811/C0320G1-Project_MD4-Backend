package md4.bid_project.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

//creator Nguyen Phi Son
@Data
public class ProductCreateDTO {
    private String name;
    private Long ownerId;
    private Long categoryId;
    private Double initialPrice;
    private Double increaseAmount;
    private LocalDateTime registerDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private Long approvementStatusId;
}
