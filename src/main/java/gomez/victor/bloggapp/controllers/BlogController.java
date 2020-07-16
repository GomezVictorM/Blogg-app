package gomez.victor.bloggapp.controllers;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// As a reference https://spring.io/guides/gs/accessing-data-jpa/
// http://zetcode.com/spring/getmapping/
// https://www.tutorialspoint.com/spring/spring_autowired_annotation.htm

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

/*  Just to test the localhost 8080
    @GetMapping(value = "/")
    public String testConnection() {
        return "Hej Spring";
    }
 */

    @GetMapping(value = "/articles")
    public List<Article> articles() {
        return postService.getAllArticles();
    }

    // Aa a reference, read: https://www.javadevjournal.com/spring/spring-request-response-body/
    @PostMapping(value = "/article")
    public void publishedArticle(@RequestBody Article article) {
        postService.insert(article);
    }

}
