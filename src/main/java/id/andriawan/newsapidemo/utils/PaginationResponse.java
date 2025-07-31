package id.andriawan.newsapidemo.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaginationResponse(
        @JsonProperty("current_page")
        int currentPage,
        @JsonProperty("per_page")
        int perPage,
        @JsonProperty("total_page")
        int totalPage,
        @JsonProperty("total_data")
        long totalData
) {

}
