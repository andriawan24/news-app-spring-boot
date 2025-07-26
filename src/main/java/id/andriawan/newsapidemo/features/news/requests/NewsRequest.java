package id.andriawan.newsapidemo.features.news.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewsRequest {
    @NotBlank(message = "title cannot be blank")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    @NotBlank(message = "content cannot be blank")
    private String content;

    @NotBlank(message = "author cannot be blank")
    @Size(max = 100, message = "Author name cannot be longer than 100 characters")
    private String author;

    @NotEmpty(message = "category_id cannot be blank")
    private String categoryId;

    public void setCategory_id(String categoryId) {
        this.categoryId = categoryId;
    }
}
