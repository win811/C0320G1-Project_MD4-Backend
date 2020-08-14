package md4.bid_project.models.dto;

import lombok.Data;

public class DealManageDTO {
    private Long id;
    private String codeOrder;
    private String winBiddingTime;
    private String nameSeller;
    private String nameBuyer;
    private String nameProduct;
    private String startingBidPrice;
    private String closingBidPrice;
    private Integer amount;
    private Double totalPayment;
    private Double serviceFee;
    private String statusOfDeal;
    private boolean isDelete;

}
