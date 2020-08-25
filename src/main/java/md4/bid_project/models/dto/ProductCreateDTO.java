package md4.bid_project.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
    private List<String> productImages;
}

