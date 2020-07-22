package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() { return articleRepository.findAll(); }

    public void insert(Article article) {
        articleRepository.save(article);
    }

    public boolean deletePost(Long postId){
        Optional<Article> thePost = articleRepository.findById(postId);
        if(thePost == null)
            return false;
        articleRepository.deleteById(postId);
        return true;
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    //public Article find(Long postId) { return articleRepository.findArticleId(postId); }

}
