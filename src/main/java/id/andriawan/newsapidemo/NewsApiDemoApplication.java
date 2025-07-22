package id.andriawan.newsapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NewsApiDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsApiDemoApplication.class, args);
    }
}
