package id.andriawan.newsapidemo.features.news.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNewsRequest {
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    private String content;

    @Size(max = 100, message = "Author name cannot be longer than 100 characters")
    private String author;

    private String categoryId;

    public void setCategory_id(String categoryId) {
        this.categoryId = categoryId;
    }
}
