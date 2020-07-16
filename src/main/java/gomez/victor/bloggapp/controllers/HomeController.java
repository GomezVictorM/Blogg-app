package gomez.victor.bloggapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping(value="/")
    public String index(){
        return "index";
    }

    @GetMapping(value="article/{id}")
    public String singleArticle(){
        return "article";
    }
}
