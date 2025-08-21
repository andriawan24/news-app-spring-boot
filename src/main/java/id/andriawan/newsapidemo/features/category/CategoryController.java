package id.andriawan.newsapidemo.features.category;

import id.andriawan.newsapidemo.features.category.requests.CategoryRequest;
import id.andriawan.newsapidemo.features.category.responses.CategoryResponse;
import id.andriawan.newsapidemo.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<List<CategoryResponse>>> getCategories() {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(
                new ResponseData<>(
                        categories,
                        "success"
                )
        );
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<CategoryResponse>> addCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        ResponseData<CategoryResponse> data = new ResponseData<>(response, "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<CategoryResponse>> addCategory(@PathVariable String id, @Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryService.updateCategory(id, request);
        ResponseData<CategoryResponse> data = new ResponseData<>(response, "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<String>> deleteCategory(@PathVariable String id) {
        String resultId = categoryService.deleteCategory(id);
        ResponseData<String> response = new ResponseData<>(resultId, "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

