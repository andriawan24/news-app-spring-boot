package id.andriawan.newsapidemo.features.category;

import id.andriawan.newsapidemo.features.category.requests.CategoryRequest;
import id.andriawan.newsapidemo.features.category.responses.CategoryResponse;
import jakarta.transaction.Transactional;
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

    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName()))
            throw new IllegalArgumentException("Category with this name is already exists");

        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.saveAndFlush(category);

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    @Transactional
    public CategoryResponse updateCategory(String id, CategoryRequest request) {
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

    @Transactional
    public String deleteCategory(String id) {
        if (!categoryRepository.existsById(id))
            throw new IllegalArgumentException("Category with id is not exists");

        categoryRepository.deleteById(id);

        return id;
    }
}
