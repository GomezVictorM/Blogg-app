package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void insert(Article article) {
        articleRepository.save(article);
    }

    public boolean deletePost(Long Id){
        Optional<Article> thePost = articleRepository.findById(Id);
        if(thePost == null)
            return false;
        articleRepository.deleteById(Id);
        return true;
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

}
