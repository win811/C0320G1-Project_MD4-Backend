package md4.bid_project.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//creator : đức thoong
@Getter
@Setter
@NoArgsConstructor
public class ProductPromotionDto {

    @NotNull(message = "Không được để trống")
    private Long id;

    @NotNull(message = "Không được để trống")
    Long idProduct;

    @NotNull(message = "Không được để trống")
    private String content;

    @NotNull(message = "Không được để trống")
    private LocalDateTime startDate;

    @NotNull(message = "Không được để trống")
    private LocalDateTime endDate;

    @NotNull(message = "Không được để trống")
    @Min(0)
    private Double percent;

    @NotNull(message = "Không được để trống")
    @Min(0)
    private Double price;


}
