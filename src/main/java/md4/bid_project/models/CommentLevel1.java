package md4.bid_project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "commentLevel1")
    private List<CommentLevel2> commentLevel2s;
}
