package md4.bid_project.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchField {

    private String name;

    private String category;

    private String minPrice;

    private String maxPrice;

    private String owner;

    private String status;
}
