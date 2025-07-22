package id.andriawan.newsapidemo.features.category.responses;

import java.time.Instant;

public record CategoryResponse(
        String id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
