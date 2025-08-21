package id.andriawan.newsapidemo.features.authentication.responses;

import lombok.Data;

@Data
public class RegisterResponse {
    private String id;
    private String fullName;
    private String email;
    private String token;
}
