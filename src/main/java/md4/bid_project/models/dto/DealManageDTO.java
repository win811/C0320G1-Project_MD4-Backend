package md4.bid_project.models.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

//all created by Thao
@Data
@Entity
@Table(name = "deal_manage_dto_view")
public class DealManageDTO {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code_deal")
    private String codeDeal;

    @Column(name = "win_bidding_time")
    private LocalDateTime winBiddingTime;

    @Column(name = "name_seller")
    private String nameSeller;

    @Column(name = "name_buyer")
    private String nameBuyer;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "starting_bid_price")
    private Double startingBidPrice;

    @Column(name = "closing_bid_price")
    private Double closingBidPrice;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total_payment")
    private Double totalPayment;

    @Column(name = "service_fee")
    private Double serviceFee;

    @Column(name = "status_of_deal")
    private String statusOfDeal;

    public void setTotalPayment() {
        this.totalPayment = this.amount * this.closingBidPrice;
    }

    public void setServiceFee() {
        this.serviceFee = this.totalPayment * 0.1;
    }
}
