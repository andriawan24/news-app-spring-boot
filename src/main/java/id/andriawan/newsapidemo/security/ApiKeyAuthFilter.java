package id.andriawan.newsapidemo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.andriawan.newsapidemo.utils.ResponseError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    public ApiKeyAuthFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Value("${news.api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestApiKey = request.getHeader("X-Api-Key");

        if (apiKey.equals(requestApiKey)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    "api-user",
                    null,
                    Collections.emptyList()
            );
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            ResponseError errorResponse = new ResponseError("Invalid api key", Instant.now());
            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }
}
