package id.andriawan.newsapidemo.features.category;

import id.andriawan.newsapidemo.features.category.requests.CreateCategoryRequest;
import id.andriawan.newsapidemo.features.category.requests.UpdateCategoryRequest;
import id.andriawan.newsapidemo.features.category.responses.CategoryResponse;
import id.andriawan.newsapidemo.utils.ResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseData<List<CategoryResponse>>> getCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(
                new ResponseData<>(
                        categories,
                        "success"
                )
        );
    }

    @PostMapping
    public ResponseEntity<ResponseData<CategoryResponse>> addCategory(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        ResponseData<CategoryResponse> data = new ResponseData<>(response, "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<CategoryResponse>> addCategory(@PathVariable String id, @Valid @RequestBody UpdateCategoryRequest request) {
        CategoryResponse response = categoryService.updateCategory(id, request);
        ResponseData<CategoryResponse> data = new ResponseData<>(response, "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
}

