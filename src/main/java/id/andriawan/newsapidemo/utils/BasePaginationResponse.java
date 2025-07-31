package id.andriawan.newsapidemo.utils;

public record BasePaginationResponse<T>(
        T data,
        PaginationResponse metadata
) {

}

