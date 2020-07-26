package gomez.victor.bloggapp.repositories;

import gomez.victor.bloggapp.entities.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    public Article findById(int id);
    public List<Article> findByThemeId(int themeId);
}
