package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public void insert(Article article) {
        articleRepository.save(article);
    }
}
