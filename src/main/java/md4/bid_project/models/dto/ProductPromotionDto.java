package md4.bid_project.models.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

//creator : đức thoonggit status
public class ProductPromotionDto {

    @NotNull(message = "Không được để trống")
    private Long id;

    @NotNull(message = "Không được để trống")
    Long idProduct;

    @NotNull(message = "Không được để trống")
    private String content;

    @NotNull(message = "Không được để trống")
    private Date startDate;

    @NotNull(message = "Không được để trống")
    private Date endDate;

    @NotNull(message = "Không được để trống")
    private Double percent;

    @NotNull(message = "Không được để trống")
    private Double price;

    public ProductPromotionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
