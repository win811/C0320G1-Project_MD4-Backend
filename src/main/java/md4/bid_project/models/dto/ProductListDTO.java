package md4.bid_project.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md4.bid_project.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ProductListDTO {

    private Long id;

    private String name;

    private Double initialPrice;

    private Double increaseAmount;

    private LocalDateTime registerDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String approvementStatus;

    private String description;

    private String category;

    private String owner;

    private List<String> productImages;

    private String banned;

    private String auctionStatus;

    public ProductListDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getApprovementStatus() {
        return approvementStatus;
    }

    public void setApprovementStatus(String approvementStatus) {
        this.approvementStatus = approvementStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        List<String> imageLink = new ArrayList<>();
        for (ProductImage image: productImages) {
            imageLink.add(image.getLink());
        }
        this.productImages = imageLink;
    }

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }

    public String getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(String auctionStatus) {
        this.auctionStatus = auctionStatus;
    }
}
