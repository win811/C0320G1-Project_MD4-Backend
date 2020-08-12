package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "lv1_comments")
@Data
public class Lv1Comment {

    @Id
    @Column(name = "lv1_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lv1_comment_text")
    private String lv1CommentText;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private Long userId;

}
