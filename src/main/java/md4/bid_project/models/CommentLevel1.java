package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment_level1")
@Data
public class CommentLevel1 {

    @Id
    @Column(name = "comment_level1_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_level1_content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
