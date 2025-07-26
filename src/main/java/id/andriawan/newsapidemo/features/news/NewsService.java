package id.andriawan.newsapidemo.features.news;

import id.andriawan.newsapidemo.features.category.Category;
import id.andriawan.newsapidemo.features.category.CategoryRepository;
import id.andriawan.newsapidemo.features.category.responses.CategoryResponse;
import id.andriawan.newsapidemo.features.news.requests.NewsRequest;
import id.andriawan.newsapidemo.features.news.requests.UpdateNewsRequest;
import id.andriawan.newsapidemo.features.news.responses.NewsResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final Path rootLocation;
    private final String uploadDir;

    public NewsService(
            NewsRepository newsRepository,
            CategoryRepository categoryRepository,
            @Value("${file.upload-dir}") String uploadDir
    ) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
        this.rootLocation = Paths.get(uploadDir);
        this.uploadDir = uploadDir;
    }

    public List<NewsResponse> getNews() {
        List<News> newsList = newsRepository.findAll(Sort.by("createdAt"));
        return newsList.stream().map(this::convertNewsToResponse).toList();
    }

    @Transactional
    public NewsResponse createNews(NewsRequest request, MultipartFile image) {
        // Find a category by id and return immediately if not found
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new IllegalArgumentException("Category not found")
        );

        // Save and get image url
        String imageUrl = saveImage(image);

        News news = new News();
        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setAuthor(request.getAuthor());
        news.setImageUrl(imageUrl);
        news.setCategory(category);

        newsRepository.saveAndFlush(news);

        return convertNewsToResponse(news);
    }

    @Transactional
    public NewsResponse updateNews(String id, UpdateNewsRequest request, MultipartFile image) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("News not found")
        );

        try {
            // Check on category first cause this throws exception
            if (request.getCategoryId() != null) {
                Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                        () -> new IllegalArgumentException("Category is not found")
                );
                news.setCategory(category);
            }

            // Check on the image after that cause this also throws exception
            if (image != null) {
                deleteImage(news.getImageUrl());
                news.setImageUrl(saveImage(image));
            }

            if (request.getTitle() != null) {
                news.setTitle(request.getTitle());
            }

            if (request.getContent() != null) {
                news.setContent(request.getContent());
            }

            if (request.getAuthor() != null) {
                news.setAuthor(request.getAuthor());
            }

            newsRepository.saveAndFlush(news);

            return convertNewsToResponse(news);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String deleteNews(String id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("News not found")
        );

        try {
            deleteImage(news.getImageUrl());
            newsRepository.deleteById(id);
            return id;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private NewsResponse convertNewsToResponse(News news) {
        return new NewsResponse(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getAuthor(),
                news.getImageUrl(),
                news.getUpdatedAt(),
                news.getCreatedAt(),
                new CategoryResponse(
                        news.getCategory().getId(),
                        news.getCategory().getName(),
                        news.getCategory().getCreatedAt(),
                        news.getCategory().getUpdatedAt()
                )
        );
    }

    private void deleteImage(String imageUrl) throws IOException {
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .toUriString();

        String imagePath = imageUrl.substring(url.length() + uploadDir.length() + 1);
        Path image = rootLocation.resolve(imagePath);

        if (Files.exists(image)) {
            Files.delete(image);
        }
    }

    private String saveImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("Cannot save empty file");
        }

        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String filename = UUID.randomUUID() + "_" + Objects.requireNonNull(image.getOriginalFilename()).replace(" ", "");
            Files.copy(image.getInputStream(), rootLocation.resolve(filename));
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(uploadDir + filename)
                    .toUriString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }
}
