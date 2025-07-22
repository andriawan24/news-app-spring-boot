package id.andriawan.newsapidemo.utils;

public record ResponseData<T>(
        T data,
        String message
) {
}
