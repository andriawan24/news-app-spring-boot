package id.andriawan.newsapidemo.features.authentication.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email must not be empty")
    private String name;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 19, message = "Password length must be between 8 and 19")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).+",
            flags = Pattern.Flag.UNICODE_CASE,
            message = "Must include letter and number"
    )
    private String password;
}
