package md4.bid_project.models.dto;

import md4.bid_project.models.Product;
import md4.bid_project.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCommentDTO {

    private Long id;
    private String content;
    private Map<String, String> user;
    private List<ProductCommentDTO> commentLevel2List;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;

    public ProductCommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public ProductCommentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ProductCommentDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Map<String, String> getUser() {
        return user;
    }

    public ProductCommentDTO setUser(User user) {
        Map<String, String> temp = new HashMap<>();
        temp.put("id", String.valueOf(user.getId()));
        temp.put("fullname", user.getFullname());
        this.user = temp;
        return this;
    }

    public List<ProductCommentDTO> getCommentLevel2List() {
        return commentLevel2List;
    }

    public ProductCommentDTO setCommentLevel2List(List<ProductCommentDTO> commentLevel2List) {
        this.commentLevel2List = commentLevel2List;
        return this;
    }
}
