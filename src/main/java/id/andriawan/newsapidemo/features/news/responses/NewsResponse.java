package id.andriawan.newsapidemo.features.news.responses;

import id.andriawan.newsapidemo.features.category.responses.CategoryResponse;

import java.time.Instant;

public record NewsResponse(
        String id,
        String title,
        String content,
        String author,
        String imageUrl,
        Instant updatedAt,
        Instant createdAt,
        CategoryResponse category
) {
}
