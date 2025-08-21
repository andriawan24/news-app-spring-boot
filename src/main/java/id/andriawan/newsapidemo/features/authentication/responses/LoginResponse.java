package id.andriawan.newsapidemo.features.authentication.responses;

import java.util.Date;

public record LoginResponse(
        String token,
        Date expiredAt
) {
}
