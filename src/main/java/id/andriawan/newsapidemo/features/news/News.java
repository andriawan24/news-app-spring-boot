package id.andriawan.newsapidemo.features.news;

import id.andriawan.newsapidemo.features.category.Category;
import id.andriawan.newsapidemo.utils.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @Size(max = 200)
    @NotNull
    @Column(name = "author", nullable = false, length = 200)
    private String author;

    @Size(max = 200)
    @NotNull
    @Column(name = "image_url", nullable = false, length = 200)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;
}
