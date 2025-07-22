package id.andriawan.newsapidemo.features.category.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotBlank(message = "Category name should be not blank")
    private String name;
}

