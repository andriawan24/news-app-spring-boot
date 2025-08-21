package id.andriawan.newsapidemo.features.authentication;

import id.andriawan.newsapidemo.features.authentication.requests.LoginRequest;
import id.andriawan.newsapidemo.features.authentication.requests.RegisterRequest;
import id.andriawan.newsapidemo.features.authentication.responses.LoginResponse;
import id.andriawan.newsapidemo.services.JwtService;
import id.andriawan.newsapidemo.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = authService.login(loginRequest);

        UserSecurity userSecurity = userDetailsService.loadUserByUsername(user.getEmail());

        String jwtToken = jwtService.generateToken(userSecurity);
        Date expiredAt = jwtService.extractExpiration(jwtToken);

        ResponseData<LoginResponse> response = new ResponseData<>(new LoginResponse(jwtToken, expiredAt), "success");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authService.register(registerRequest);

        return ResponseEntity.ok(new ResponseData<>(user, "success"));
    }
}
