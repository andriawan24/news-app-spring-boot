package id.andriawan.newsapidemo.features.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    @Query(
            value = "SELECT * FROM news " +
                    "WHERE document_vector @@ to_tsquery('english', :query) " +
                    "ORDER BY ts_rank_cd(document_vector, to_tsquery('english', :query)) DESC",
            countQuery = "SELECT count(*) FROM news WHERE document_vector @@ to_tsquery('english', :query)",
            nativeQuery = true
    )
    Page<News> findSimilarNews(@Param("query") String query, Pageable pageable);
}
