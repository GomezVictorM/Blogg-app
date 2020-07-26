package gomez.victor.bloggapp.controllers;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.findAllArticles();
    }

    @GetMapping("/articles/{themeId}")
    public List<Article> getArticlesByThemeId(@PathVariable int themeId) {
        return articleService.findArticlesByThemeId(articleId);
    }

    @PostMapping("/articles")
    public Article sendOneMessage(@RequestBody Article message) {
        return articleService.postArticle(message);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleService.deleteOneArticle(id);
    }
}
