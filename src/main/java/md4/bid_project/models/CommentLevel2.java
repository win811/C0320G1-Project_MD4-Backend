package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="lv2_commemts")
@Data
public class CommentLevel2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lv2_comment_id")
    private long id;

    @Column(name = "lv2_comment_text")
    private String text;

//    @ManyToOne
//    @JoinColumn(name="lv1_comment_id")
//    private CommentLevel1 commentLevel1;

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;

//    @ManyToOne
//    @JoinColumn(name="product_id")
//    private Product product;
}
