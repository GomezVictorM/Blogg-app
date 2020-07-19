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

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public void insert(Article article) {
        articleRepository.save(article);
    }

    public boolean deletePost(Long Id){
        Article thePost = articleRepository.findOne(Id);
        if(thePost == null)
            return false;
        articleRepository.delete(Id);
        return true;
    }

    public Article getArticle(Long id) {
        return articleRepository.findOne(id);
    }

}
