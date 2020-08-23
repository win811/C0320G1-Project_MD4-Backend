package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank(message = "Vui lòng nhập tên sản phẩm")
    @Pattern(regexp = "[A-Z]{1}[ a-zA-Z0-9_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+$", message = "Tên không hợp lệ")
    @Column(name = "product_name")
    private String name;

    @Min(value = 0, message = "Giá không được âm")
    @Column(name = "product_initial_price")
    private Double initialPrice;

    @Min(value = 0,message = "Bước giá không được âm")
    @Column(name = "product_increase_amount")
    private Double increaseAmount;

    @Column(name = "product_register_date")
    private LocalDateTime registerDate;

    @Column(name = "product_start_date")
    private LocalDateTime startDate;

    @Column(name = "product_end_date")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "product_approvement_status_id")
    private ApprovementStatus approvementStatus;

    @NotBlank(message = "Vui lòng nhập mô tả sản phẩm")
    @Column(name = "product_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "product_owner_id")
    private User owner;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value = "product")
    private List<ProductImage> productImages;

    @Column(name = "product_status")
    private Boolean status;

    @Column(name = "product_banned")
    private String banned;

    @OneToOne(mappedBy = "product")
    @JsonIgnoreProperties(value = "product")
    private Auction auction;

}
