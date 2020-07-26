package gomez.victor.bloggapp.repositories;

import gomez.victor.bloggapp.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository <Article, Integer> {
    public Article findById(int id);
    public List<Article> findByArticleId(int channelId);
}
