package id.andriawan.newsapidemo.features.category;

import id.andriawan.newsapidemo.features.category.requests.CreateCategoryRequest;
import id.andriawan.newsapidemo.features.category.requests.UpdateCategoryRequest;
import id.andriawan.newsapidemo.features.category.responses.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by("createdAt"));

        return categories.stream().map(category ->
                new CategoryResponse(category.getId(), category.getName(), category.getCreatedAt(), category.getUpdatedAt())
        ).toList();
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        categoryRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(_ -> {
                    throw new IllegalArgumentException("Category with this name is already exists");
                });

        Category newCategory = new Category();
        newCategory.setName(request.getName());

        Category savedCategory = categoryRepository.save(newCategory);

        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getCreatedAt(),
                savedCategory.getUpdatedAt()
        );
    }

    public CategoryResponse updateCategory(String id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with this name is already exists"));

        category.setName(request.getName());

        Category savedCategory = categoryRepository.save(category);

        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getCreatedAt(),
                savedCategory.getUpdatedAt()
        );
    }
}
