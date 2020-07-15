package gomez.victor.bloggapp.repositories;

import gomez.victor.bloggapp.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    List<Article> findById(Long id);

}
