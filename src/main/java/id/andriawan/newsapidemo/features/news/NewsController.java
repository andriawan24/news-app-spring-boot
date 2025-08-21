package id.andriawan.newsapidemo.features.news;

import id.andriawan.newsapidemo.features.news.requests.NewsRequest;
import id.andriawan.newsapidemo.features.news.requests.UpdateNewsRequest;
import id.andriawan.newsapidemo.features.news.responses.NewsResponse;
import id.andriawan.newsapidemo.utils.BasePaginationResponse;
import id.andriawan.newsapidemo.utils.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "News")
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasePaginationResponse<List<NewsResponse>>> getAllNews(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query
    ) {
        BasePaginationResponse<List<NewsResponse>> newsResponses = newsService.getNews(query, page, size);
        return ResponseEntity.ok(newsResponses);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseData<NewsResponse>> createNews(
            @RequestParam("image") MultipartFile imageFile,
            @Valid @ModelAttribute NewsRequest newsRequest
    ) {
        NewsResponse newsResponse = newsService.createNews(newsRequest, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseData<>(
                        newsResponse,
                        "success"
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<NewsResponse>> updateNews(
            @PathVariable String id,
            @RequestParam(value = "image", required = false) MultipartFile imageFile,
            @Valid @ModelAttribute UpdateNewsRequest newsRequest
    ) {
        NewsResponse response = newsService.updateNews(id, newsRequest, imageFile);
        return ResponseEntity.ok(new ResponseData<>(response, "success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> deleteNews(@PathVariable String id) {
        String newsId = newsService.deleteNews(id);
        return ResponseEntity.ok(new ResponseData<>(newsId, "success"));
    }
}
