package md4.bid_project.models.dto;

import lombok.Data;
import md4.bid_project.models.ProductImage;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCreateDTO {
    private String name;
    private Long ownerId;
    private Long categoryId;
    private Double initialPrice;
    private Double increaseAmount;
    private Date registerDate;
    private Date startDate;
    private Date endDate;
    private String description;
    private Long approvementStatusId;
    private String productImage;

    public ProductCreateDTO() {
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImages) {
        this.productImage = productImages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Double getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(Double increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getApprovementStatusId() {
        return approvementStatusId;
    }

    public void setApprovementStatusId(Long approvementStatusId) {
        this.approvementStatusId = approvementStatusId;
    }
}

