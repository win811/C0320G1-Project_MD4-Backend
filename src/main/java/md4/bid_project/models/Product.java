package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
//Coder: Nguyen Thanh Tu
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_initial_price")
    private Double initialPrice;

    @Column(name = "product_increase_amount")
    private Double increaseAmount;

    @Column(name = "product_register_date")
    private Date registerDate;

    @Column(name = "product_start_date")
    private Date startDate;

    @Column(name = "product_end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "product_approvement_status_id")
    private ApprovementStatus approvementStatus;

    @Column(name = "product_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "product_owner_id")
    private User owner;

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

    public ApprovementStatus getApprovementStatus() {
        return approvementStatus;
    }

    public void setApprovementStatus(ApprovementStatus approvementStatus) {
        this.approvementStatus = approvementStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
