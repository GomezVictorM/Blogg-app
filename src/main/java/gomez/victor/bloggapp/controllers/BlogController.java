package gomez.victor.bloggapp.controllers;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// As a reference https://spring.io/guides/gs/accessing-data-jpa/
// http://zetcode.com/spring/getmapping/
// https://www.tutorialspoint.com/spring/spring_autowired_annotation.htm

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/articles")
    public List<Article> articles() {
        return postService.getAllArticles();
    }

    @GetMapping(value="/the_post/{id}")
    public Optional<Article> getPostById(@PathVariable Long id){
        return postService.getArticle(id);
    }

    public boolean deletePost(@PathVariable long id) {
        return postService.deletePost(id);
    }

    // As a reference, read: https://www.javadevjournal.com/spring/spring-request-response-body/
    @PostMapping(value = "/article")
    public String publishedArticle(@RequestBody Article article) {
        if(article.getPublishedDate() == null)
            article.setPublishedDate(new Date());
        postService.insert(article);
        return "Article was published";
    }

}
