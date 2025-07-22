package id.andriawan.newsapidemo.utils;

import java.time.Instant;

public record ResponseError(
        String message,
        Instant timestamp
) { }

