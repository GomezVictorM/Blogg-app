package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.entities.Theme;
import gomez.victor.bloggapp.entities.User;
import gomez.victor.bloggapp.repositories.ArticleRepository;
import gomez.victor.bloggapp.repositories.ThemeRepository;
import gomez.victor.bloggapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private SocketService socketService;

    public Article postArticle(Article article) {
        Article dbArticle = null;

        try{
            dbArticle = articleRepository.save(article);
            ArrayList<Article> articles = new ArrayList<>();
            articles.add(dbArticle);
            dbArticle = addSenderName(articles).get(0);
            socketService.sendToAll(dbArticle, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dbArticle;
    }

    public List<Article> findAllArticles() {
        List<Article> articles = (List<Article>) articleRepository.findAll();


        articles.forEach(article -> {
            User sender = userRepository.findById(article.getSenderId());
            article.setSender(sender);

            User receiver = userRepository.findById(article.getReceiverId());
            article.setReceiver(receiver);

            Integer theme_id = article.getThemeId();
            if (theme_id != null) {
                Theme theme = themeRepository.findById((int) theme_id);
                article.setTheme(theme);
            }
        });

        return articles;
    }

    public Article findOneArticle(int id) {
        Article article = articleRepository.findById(id);
        if (article == null) return null;

        User sender = userRepository.findById(article.getSenderId());
        article.setSender(sender);

        User receiver = userRepository.findById(article.getReceiverId());
        article.setReceiver(receiver);

        Integer theme_id = article.getThemeId();
        Theme theme;
        if (theme_id != null) {
            theme = themeRepository.findByID((int) theme_id);
        } else {
            theme = new Theme();
            theme.setId(0);
            theme.setTitle("Public Theme");
        }
        article.setTheme(theme);

        return article;
    }

    public List<Article> findArticleByThemeId(int themeId) {
        List<Article> articles = articleRepository.findByArticleId(themeId);
        addSenderName(articles);

        for (Article article: articles){
            User sender = userRepository.findById(article.getSenderId());
            article.setSender(sender);
        }

        return articles;
    }

    public List<Article> addSenderName(List<Article> articles) {
        for (int i = 0; i < articles.size(); i++) {
            try {
                User user = userRepository.findById(articles.get(i).getSenderId());
                articles.get(i).setSenderName(user.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return articles;
    }

    public void deleteOneArticle(int id) {
        articleRepository.deleteById(id);
    }

    public Article createNewArticle(Article newArticle) {
        Article dbArticle = null;
        try {
            dbArticle = articleRepository.save(newArticle);
            dbArticle.action = "delete-article";
            socketService.sendToAll(dbArticle, Article.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dbArticle;
    }

}
