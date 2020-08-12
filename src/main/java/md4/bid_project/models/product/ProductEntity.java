package md4.bid_project.models.product;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;


    @Column(name="product_name")
    private String product_name;


    @Column(name="product_initial_price")
    private  double product_initial_price;


    @Column(name="product_increase_amount")
    private  double product_increase_amount;


    @Column(name="product_register_date")
    private Date product_register_date;

    @Column(name="product_start_date")
    private Date product_start_date;

    @Column(name="product_end_date")
    private Date product_end_date;

    @Column(name="product_description")
    private String product_description;

    @Column(name="product_approvement_status_id")
    private Long product_approvement_status_id;

    @Column(name="product_category_id")
    private Long product_category_id;

    @Column(name = "product_owner_id")
    private Long product_owner_id;


//    @Column(name="product_img")
//    private String product_img;


    public ProductEntity() {
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_initial_price() {
        return product_initial_price;
    }

    public void setProduct_initial_price(double product_initial_price) {
        this.product_initial_price = product_initial_price;
    }

    public double getProduct_increase_amount() {
        return product_increase_amount;
    }

    public void setProduct_increase_amount(double product_increase_amount) {
        this.product_increase_amount = product_increase_amount;
    }

    public Date getProduct_register_date() {
        return product_register_date;
    }

    public void setProduct_register_date(Date product_register_date) {
        this.product_register_date = product_register_date;
    }

    public Date getProduct_start_date() {
        return product_start_date;
    }

    public void setProduct_start_date(Date product_start_date) {
        this.product_start_date = product_start_date;
    }

    public Date getProduct_end_date() {
        return product_end_date;
    }

    public void setProduct_end_date(Date product_end_date) {
        this.product_end_date = product_end_date;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public Long getProduct_approvement_status_id() {
        return product_approvement_status_id;
    }

    public void setProduct_approvement_status_id(Long product_approvement_status_id) {
        this.product_approvement_status_id = product_approvement_status_id;
    }

    public Long getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(Long product_category_id) {
        this.product_category_id = product_category_id;
    }

    public Long getProduct_owner_id() {
        return product_owner_id;
    }

    public void setProduct_owner_id(Long product_owner_id) {
        this.product_owner_id = product_owner_id;
    }

}

