package md4.bid_project.models.dto;

//all created by Thao
import java.time.LocalDateTime;

public class DealManageDTO {
    private Long id;
    private String code;
    private LocalDateTime winBiddingTime;
    private String nameSeller;
    private String nameBuyer;
    private String nameProduct;
    private Double startingBidPrice;
    private Double closingBidPrice;
    private Integer amount;
    private Double totalPayment;
    private Double serviceFee;
    private String statusOfDeal;
//    private boolean isDelete;


    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getWinBiddingTime() {
        return winBiddingTime;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public String getNameBuyer() {
        return nameBuyer;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public Double getStartingBidPrice() {
        return startingBidPrice;
    }

    public Double getClosingBidPrice() {
        return closingBidPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public String getStatusOfDeal() {
        return statusOfDeal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setWinBiddingTime(LocalDateTime winBiddingTime) {
        this.winBiddingTime = winBiddingTime;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public void setNameBuyer(String nameBuyer) {
        this.nameBuyer = nameBuyer;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setStartingBidPrice(Double startingBidPrice) {
        this.startingBidPrice = startingBidPrice;
    }

    public void setClosingBidPrice(Double closingBidPrice) {
        this.closingBidPrice = closingBidPrice;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setStatusOfDeal(String statusOfDeal) {
        this.statusOfDeal = statusOfDeal;
    }

    public void setTotalPayment() {
        this.totalPayment = this.amount * this.closingBidPrice;
    }

    public void setServiceFee() {
        this.serviceFee = this.totalPayment * 0.1;
    }
}
