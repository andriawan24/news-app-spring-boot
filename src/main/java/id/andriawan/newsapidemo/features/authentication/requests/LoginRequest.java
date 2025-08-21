package id.andriawan.newsapidemo.features.authentication.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password must not be empty")
    private String password;
}
